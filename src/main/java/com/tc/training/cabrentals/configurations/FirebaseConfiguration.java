package com.tc.training.cabrentals.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Bean
    public void startFireBase() {
        try {
            InputStream inputStream = new ClassPathResource("cab-rentals-firebase-secret.json").getInputStream();
            FirebaseOptions build = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(inputStream)).build();
            FirebaseApp.initializeApp(build);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
