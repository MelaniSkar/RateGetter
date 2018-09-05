package com.example.rateGetter.publisher;

import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import org.springframework.stereotype.Component;


@Component
public class TickerMessagePublisher extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    EventBus eventBus = vertx.eventBus();
    vertx.setPeriodic(1000, event -> {
      eventBus.publish(Addresses.MAKE_REQUEST_ADDRESS, "get BTC-USD rate from Bittrex");
      //message must be an object and have two params (currency pair and exchange)
    });
  }
}
