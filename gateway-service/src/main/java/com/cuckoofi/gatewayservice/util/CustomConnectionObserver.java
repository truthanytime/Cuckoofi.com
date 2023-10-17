package com.cuckoofi.gatewayservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import reactor.netty.Connection;
import reactor.netty.ConnectionObserver;

@Slf4j
public class CustomConnectionObserver implements ConnectionObserver {

    public CustomConnectionObserver() {
    }

    @Override
    public void onStateChange(@Nullable Connection connection, @Nullable State newState) {

        if (newState == State.DISCONNECTING && connection != null) {
            log.info("Client connection closed: {}", connection.channel().remoteAddress());
        }
    }
}