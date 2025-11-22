package com.springSecure.springSecure.config;

import com.springSecure.springSecure.service.MyUserDetailsService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// when ran this method will generate an object with configuration details
// then the configuration details are given to ioc

public class securityConfiguration {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth.
                  //      requestMatchers("/api/v1/admin/welcome").hasRole("ADMIN")
                                //.
                        requestMatchers("/welcomepage","/register","/api/v1/admin/welcome").permitAll()
                        //.authenticated()
                     .anyRequest().authenticated())
                 .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
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
@Bean
    public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
    
}

}
