package com.example.nottwitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeenConfig {

    @Bean
    public PasswordEncoder getPassword(){
        return new BCryptPasswordEncoder(8);
    }

}
