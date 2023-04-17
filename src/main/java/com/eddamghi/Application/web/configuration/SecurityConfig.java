package com.eddamghi.Application.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder.encode("0000")).roles("ADMIN","CLIENT").build(),
                User.withUsername("client").password(passwordEncoder.encode("1111")).roles("CLIENT").build()
        );
    }
    // bean annotation means that this method will run when the application starts
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity.formLogin();
        httpsecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
        httpsecurity.authorizeHttpRequests().requestMatchers("/client/**").hasRole("CLIENT");
        httpsecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpsecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpsecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpsecurity.build();
    }
}
