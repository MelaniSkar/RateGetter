package com.example.rateGetter.listener;

import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class RequestMessageListener extends AbstractVerticle {

  @Override
  public void start() {
    vertx.eventBus().consumer(Addresses.HANDLE_RESPONSE_ADDRESS, handleMessage());
  }

  public Handler<Message<JsonObject>> handleMessage() {
    return msg -> {
        JsonObject body = msg.body();
        JsonArray result = body.getJsonArray("result");
        JsonObject arrayElem = result.getJsonObject(0);
        Double BTC_rate = arrayElem.getDouble("Last");
        System.out.println(BTC_rate);
    };
  }
}
