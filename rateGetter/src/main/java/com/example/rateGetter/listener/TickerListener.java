package com.example.rateGetter.listener;

import com.example.rateGetter.eventBus.Addresses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TickerListener extends AbstractVerticle {

  @Override
  public void start() {

  }

  //получает сообщение, парсит его и делает запрос на биржу, получает response
  @EventListener //нужно указать адрес
  public void onMessageReceived(Message message) {
    WebClient client = WebClient.create(vertx);
    client
      .getAbs(Addresses.bittrexurl) //creates Http GET request
      .as(BodyCodec.jsonObject())
      .send(handleMessage());
  }

  public Handler<AsyncResult<HttpResponse<JsonObject>>> handleMessage() {
    return ar -> {
      if (ar.succeeded()) {
        EventBus eventBus = vertx.eventBus();
        eventBus.publish(Addresses.HANDLE_RESPONSE_ADDRESS, ar.result());
      } else {

      }
    };
  }
}
