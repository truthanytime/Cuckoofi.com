package com.cuckoofi.authservice.configuration;

import com.cuckoofi.commonclientlibs.jwt.JwtAccessDeniedHandler;
import com.cuckoofi.commonclientlibs.jwt.JwtAuthEntryPoint;
import com.cuckoofi.commonclientlibs.jwt.JwtAuthTokenFilter;
import com.cuckoofi.commonclientlibs.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtAuthEntryPoint unauthorizedHandler;

    @Autowired
    public JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    public JwtAuthTokenFilter authenticationJwtTokenFilter;

    public static final String[] ENDPOINTS_WHITELIST = {
            "/actuator",
            "/actuator/**",
            "/signin",
            "/signup",
            "/checkUserAlreadyExists",
            "/forgotPassword",
            "/resetPassword",
            "/register-from-google",
            "/resetPassword/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/swagger-ui/index.html",
            "/swagger-ui.html",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->authorize
                        .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(unauthorizedHandler)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
