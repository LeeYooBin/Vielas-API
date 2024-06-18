package com.programarcomvoce.vielas.infra.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
// import java.io.InputStream;

@Configuration
public class FirebaseConfig {
  @Bean
  public FirebaseApp initializeFirebaseApp() throws IOException {
    // ClassLoader classLoader = FirebaseConfig.class.getClassLoader();
    // InputStream serviceAccount = classLoader.getResourceAsStream("serviceAccountKey.json");

    // if (serviceAccount == null) {
    //   throw new IllegalArgumentException("Arquivo serviceAccountKey.json não encontrado");
    // }

    FirebaseOptions options = FirebaseOptions.builder()
      .setCredentials(GoogleCredentials.getApplicationDefault())
      .build();

    return FirebaseApp.initializeApp(options);
  }
}
