package com.example.rateGetter.listener;

import com.example.rateGetter.config.URLS;
import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.springframework.stereotype.Component;

@Component
public class TickerListener extends AbstractVerticle {

  @Override
  public void start() {
    vertx.eventBus().consumer(Addresses.MAKE_REQUEST_ADDRESS, message -> {
      WebClient client = WebClient.create(vertx);
      client
        .getAbs(URLS.BITTREX_URL) //creates Http GET request
        .as(BodyCodec.jsonObject())
        .send(handleResponse());
    });
  }

  public Handler<AsyncResult<HttpResponse<JsonObject>>> handleResponse() {
    return ar -> {
      if (ar.succeeded()) {
        vertx.eventBus().publish(Addresses.HANDLE_RESPONSE_ADDRESS, ar.result().body());
      } else {
        System.out.println("ar did not succeed");
      }
    };
  }
}
