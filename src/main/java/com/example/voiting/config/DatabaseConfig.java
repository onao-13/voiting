package com.example.voiting.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class DatabaseConfig {

    @PostConstruct
    private void postConstruct() throws IOException {
        FirebaseApp.initializeApp(getFirebaseOptions());
    }

    @Bean
    FileInputStream getAccountService() throws FileNotFoundException {
        return new FileInputStream("serviceAccount.json");
    }

    @Bean
    FirebaseOptions getFirebaseOptions() throws IOException {
        return FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(getAccountService()))
            .build();
    }
}
