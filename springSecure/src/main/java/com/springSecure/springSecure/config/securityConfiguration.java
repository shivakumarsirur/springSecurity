package com.springSecure.springSecure.config;

import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// when ran this method will generate an object with configuration details
// then the configuration details are given to ioc

public class securityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
     return    httpSecurity.csrf(csrf->csrf.disable())

                .authorizeHttpRequests(auth->auth.
                        requestMatchers("/welcomepage","/register").authenticated().anyRequest().permitAll())
                .build();
    }
@Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
       return configuration.getAuthenticationManager();
}

}
