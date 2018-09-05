package com.example.rateGetter;


import com.example.rateGetter.listener.*;
import com.example.rateGetter.publisher.TickerMessagePublisher;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.spi.VerticleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

  @Autowired
  private RequestMessageListener requestMessageListener;
  @Autowired
  private TickerListener tickerListener;
  @Autowired
  private TickerMessagePublisher tickerMessagePublisher;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void deployVerticles() {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(requestMessageListener);
    vertx.deployVerticle(tickerListener);
    vertx.deployVerticle(tickerMessagePublisher);
  }
}
