package com.tanou.projet.oc.backend.projet2;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tanou.projet.oc.backend.projet2"})
public class Application {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("DB_URL", dotenv.get("DB_URL"));
    System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    System.setProperty("SERVER_BASE_URL", dotenv.get("SERVER_BASE_URL"));

    SpringApplication.run(Application.class, args);
  }



}
