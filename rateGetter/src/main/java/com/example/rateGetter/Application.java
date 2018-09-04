package com.example.rateGetter;


import com.example.rateGetter.publisher.TickerMessagePublisher;
import io.vertx.core.Vertx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void deployVerticle() {
    Vertx.vertx().deployVerticle(TickerMessagePublisher);
  }
}
