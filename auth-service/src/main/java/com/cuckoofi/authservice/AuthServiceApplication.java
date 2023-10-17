package com.cuckoofi.authservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.TimeZone;

@EnableFeignClients(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.authservice"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info =
@Info(title = "Auth service API", version = "1.0", description = "Documentation Auth service API v1.0")
)
@AutoConfigurationPackage
@ComponentScan(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.authservice"})
@EntityScan(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.authservice"})
@EnableJpaRepositories(basePackages = {"com.cuckoofi.commonclientlibs", "com.cuckoofi.authservice"})
public class AuthServiceApplication {

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("auth-service application running in UTC timezone :" + new Date());
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
