package com.cuckoofi.pricelineservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.pricelineservice"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info =
@Info(title = "Priceline service API", version = "1.0", description = "Documentation Priceline service API v1.0")
)
@AutoConfigurationPackage
@ComponentScan(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.pricelineservice"})
@EntityScan(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.pricelineservice"})
@EnableJpaRepositories(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.pricelineservice"})
public class PricelineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricelineServiceApplication.class, args);
    }

}
