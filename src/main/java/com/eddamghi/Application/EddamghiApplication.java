package com.eddamghi.Application;

import com.eddamghi.Application.DAO.entities.Client;
import com.eddamghi.Application.DAO.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class EddamghiApplication {

    private final ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(EddamghiApplication.class, args);
    }

    @Bean
    public void test() {
        var client = Client.builder()
                .name("Amine")
                .username("Amine")
                .email("amine@gmail.com")
                .build();
        clientRepository.save(client);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
