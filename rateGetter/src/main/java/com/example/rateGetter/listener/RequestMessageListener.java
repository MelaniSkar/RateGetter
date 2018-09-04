package com.example.rateGetter.listener;

import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RequestMessageListener extends AbstractVerticle {

  @Override
  public void start() {

  }

  @EventListener //нужно указать адрес
  public void onMessageReceived(JsonObject message) {
    WebClient client = WebClient.create(vertx);
    client
      .getAbs(Addresses.bittrexurl) //creates Http GET request
      .as(BodyCodec.jsonObject())
      .send(handleMessage(message));
  }

  public Handler<AsyncResult<HttpResponse<JsonObject>>> handleMessage(JsonObject jsonObject) {
    return msg -> {
        JsonArray result = jsonObject.getJsonArray("result");
        JsonObject arrayElem = result.getJsonObject(0);
        Double BTC_rate = arrayElem.getDouble("Last");
        System.out.println(BTC_rate);
    };
  }
}
