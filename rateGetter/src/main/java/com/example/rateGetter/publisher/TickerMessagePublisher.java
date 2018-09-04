package com.example.rateGetter.publisher;
import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.springframework.stereotype.Component;


@Component
public class TickerMessagePublisher extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    final EventBus eb = vertx.eventBus();
    vertx.setPeriodic(10000, event -> {
      eb.publish(Addresses.MAKE_REQUEST_ADDRESS, "get BTC-USD rate from Bittrex"); //message must be an object and have two params (currency pair and exchange)
    });
  };
}
