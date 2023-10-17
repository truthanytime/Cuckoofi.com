package com.cuckoofi.gatewayservice.filter;

import com.cuckoofi.gatewayservice.util.CustomConnectionObserver;
import com.cuckoofi.gatewayservice.util.Response;
import com.cuckoofi.gatewayservice.util.SecurityConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.netty.ConnectionObserver;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
public class AuthenticationPrefilter extends AbstractGatewayFilterFactory<AuthenticationPrefilter.Config> {

    private final List<String> excludedUrls = Arrays.asList(
            "/auth/signin",
            "/auth/signup",
            "/auth/checkUserAlreadyExists",
            "/auth/register-from-google",
            "/auth/registration-confirm",
            "/auth/forgotPassword",
            "/auth/resetPassword",
            "/v3/api-docs");

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    public AuthenticationPrefilter(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String bearerToken = request.getHeaders().getFirst(SecurityConstants.HEADER);
            String uriPath = request.getURI().getPath();

            log.info("***************************************************");
            log.info("URL is - " + request.getURI().getPath());
            log.info("Bearer Token: " + bearerToken);

            // Attach the custom ConnectionObserver to the connection
            ConnectionObserver connectionObserver = new CustomConnectionObserver();
            exchange.getAttributes().put(ConnectionObserver.class.getName(), connectionObserver);

            if (isSecured.test(request)) {
                log.info("Secured URL");

                return webClientBuilder.build().get()
                        .uri("lb://auth-service/auth/validate-token")
                        .header(SecurityConstants.HEADER, bearerToken)
                        .retrieve().bodyToMono(Void.class)
                        .map(response -> exchange)
                        .flatMap(chain::filter)
                        .onErrorResume(error -> {
                            log.error("!!!!!!!!!            Error Happened           !!!!!!!!!!", error);
                            HttpStatus errorCode;
                            String errorMsg;
                            if (error instanceof WebClientResponseException webClientException) {
                                errorCode = HttpStatus.valueOf(webClientException.getStatusCode().value());
                                webClientException.getStatusCode();
                                errorMsg = ((WebClientResponseException) error).getResponseBodyAsString();
                                JsonNode jsonNode = null;
                                try {
                                    jsonNode = objectMapper.readTree(errorMsg);
                                }
                                catch (JsonProcessingException ignored) {
                                }
                                if( jsonNode != null && jsonNode.isObject()){
                                    try {
                                        errorMsg = objectMapper.readValue(errorMsg, Response.class).getMessage();
                                    } catch (JsonProcessingException e) {
                                        errorMsg = error.getMessage();
                                    }
                                }
                            }
                            else {
                                errorCode = HttpStatus.BAD_GATEWAY;
                                errorMsg = error.getMessage();
                            }

                            return onError(exchange, errorMsg, errorCode);
                        })
                        .switchIfEmpty(chain.filter(exchange)
                                .onErrorResume(error -> {
                                    log.error("!!!!!!!!! Error Happened in requested service !!!!!!!!!!");
                                    HttpStatus errorCode;
                                    String errorMsg;
                                    if (error instanceof WebClientResponseException webClientException) {
                                        errorCode = HttpStatus.valueOf(webClientException.getStatusCode().value());
                                        errorMsg = webClientException.getResponseBodyAsString();
                                        JsonNode jsonNode = null;
                                        try {
                                            jsonNode = objectMapper.readTree(errorMsg);
                                        }
                                        catch (JsonProcessingException ignored) {
                                        }
                                        if( jsonNode != null && jsonNode.isObject()){
                                            try {
                                                errorMsg = objectMapper.readValue(errorMsg, Response.class).getMessage();
                                            } catch (JsonProcessingException e) {
                                                errorMsg = error.getMessage();
                                            }
                                        }
                                    } else {
                                        errorCode = HttpStatus.BAD_GATEWAY;
                                        errorMsg = error.getMessage();
                                    }

                                    return onError(exchange, errorMsg, errorCode);
                                })
                                .switchIfEmpty(Mono.defer(() -> {
                                    log.info("THE RESPONSE IS ARRIVED! " + uriPath);
                                    return chain.filter(exchange);
                                }))
                                .doFinally(signalType -> {
                                    if (signalType == SignalType.CANCEL ||
                                            signalType == SignalType.AFTER_TERMINATE) {
                                        log.warn("Request was canceled or interrupted. " + uriPath);
                                    }
                                    else if(signalType == SignalType.ON_ERROR) {
                                        log.warn("Error in upstream service. " + uriPath);
                                    }
                                    else{
                                        log.info("signalType is " + signalType + ". for "  + uriPath);
                                    }

                                    exchange.getAttributes().remove(ConnectionObserver.class.getName());
                                })
                        )
                        .doFinally(signalType -> {
                            if (signalType == SignalType.CANCEL ||
                                    signalType == SignalType.AFTER_TERMINATE) {
                                log.warn("Request was canceled or interrupted. " + uriPath);
                            }
                            else if(signalType == SignalType.ON_ERROR) {
                                log.warn("Error in upstream service. " + uriPath);
                            }
                            else{
                                log.info("signalType is " + signalType + ". for "  + uriPath);
                            }

                            exchange.getAttributes().remove(ConnectionObserver.class.getName());
                        });
            }


            log.info("Unsecured URL");
            return chain.filter(exchange)
                    .onErrorResume(error -> {
                        log.error("!!!!!!!!!         Error Happened in Unsecured          !!!!!!!!!!", error);
                        HttpStatus errorCode;
                        String errorMsg;
                        if (error instanceof WebClientResponseException webClientException) {
                            errorCode = HttpStatus.valueOf(webClientException.getStatusCode().value());
                            errorMsg = ((WebClientResponseException) error).getResponseBodyAsString();
                            JsonNode jsonNode = null;
                            try {
                                jsonNode = objectMapper.readTree(errorMsg);
                            }
                            catch (JsonProcessingException ignored) {
                            }
                            if( jsonNode != null && jsonNode.isObject()){
                                try {
                                    errorMsg = objectMapper.readValue(errorMsg, Response.class).getMessage();
                                } catch (JsonProcessingException e) {
                                    errorMsg = error.getMessage();
                                }
                            }
                        } else {
                            errorCode = HttpStatus.BAD_GATEWAY;
                            errorMsg = error.getMessage();
                        }

                        return onError(exchange, errorMsg, errorCode);
                    })
                    .switchIfEmpty(chain.filter(exchange)
                            .onErrorResume(error -> {
                                log.error("!!!!!!!!! Error Happened in unsecured requested service !!!!!!!!!!");
                                HttpStatus errorCode;
                                String errorMsg;
                                if (error instanceof WebClientResponseException webClientException) {
                                    errorCode = HttpStatus.valueOf(webClientException.getStatusCode().value());
                                    errorMsg = webClientException.getResponseBodyAsString();
                                    JsonNode jsonNode = null;
                                    try {
                                        jsonNode = objectMapper.readTree(errorMsg);
                                    }
                                    catch (JsonProcessingException ignored) {
                                    }
                                    if( jsonNode != null && jsonNode.isObject()){
                                        try {
                                            errorMsg = objectMapper.readValue(errorMsg, Response.class).getMessage();
                                        } catch (JsonProcessingException e) {
                                            errorMsg = error.getMessage();
                                        }
                                    }
                                } else {
                                    errorCode = HttpStatus.BAD_GATEWAY;
                                    errorMsg = error.getMessage();
                                }

                                return onError(exchange, errorMsg, errorCode);
                            })
                            .switchIfEmpty(Mono.defer(() -> {
                                log.info("THE UNSECURED RESPONSE IS ARRIVED! " + uriPath);

                                return chain.filter(exchange);
                            }))
                            .doFinally(signalType -> {
                                if (signalType == SignalType.CANCEL ||
                                        signalType == SignalType.AFTER_TERMINATE) {
                                    log.warn("Unsecured request was canceled or interrupted. " + uriPath);
                                }
                                else if(signalType == SignalType.ON_ERROR) {
                                    log.warn("Error in Unsecured upstream service. " + uriPath);
                                }
                                else{
                                    log.info("Unsecured signalType is " + signalType + ". for "  + uriPath);
                                }

                                exchange.getAttributes().remove(ConnectionObserver.class.getName());
                            })
                    )
                    .doFinally(signalType -> {
                        if (signalType == SignalType.CANCEL ||
                                signalType == SignalType.AFTER_TERMINATE) {
                            log.warn("Unsecured request was canceled or interrupted. " + uriPath);
                        }
                        else if(signalType == SignalType.ON_ERROR) {
                            log.warn("Error in Unsecured upstream service. " + uriPath);
                        }
                        else{
                            log.info("Unsecured signalType is " + signalType + ". for "  + uriPath);
                        }

                        exchange.getAttributes().remove(ConnectionObserver.class.getName());
                    });
        };
    }

    public Predicate<ServerHttpRequest> isSecured = request -> excludedUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        try {
            response.getHeaders().add("Content-Type", "application/json");
            Response<Void> respData = new Response<>(
                    httpStatus.value(),
                    false,
                    err);
            byte[] byteData = objectMapper.writeValueAsBytes(respData);
            return response.writeWith(Mono.just(byteData).map(dataBufferFactory::wrap));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response.setComplete();
    }

    @NoArgsConstructor
    public static class Config {
    }
}