package com.zenith.demo.vertxdemo;

import io.vertx.core.Vertx;

public class test {
    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        
        vertx.deployVerticle(new Service01());
        vertx.deployVerticle(new Server());
        vertx.deployVerticle(new Service03());
    }
}
