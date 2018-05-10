package com.zenith.demo.vertxdemo;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.cli.Option;
public class Service01 extends AbstractVerticle{
    public static final String URL01="VERTX_HELLO_SERVER01";
    public static final String URL02="VERTX_HELLO_SERVER02";
    
    public void start(){
        vertx.eventBus().consumer(URL01, 
                msg -> {
                    System.out.println(msg.body()); //处理请求消息
                    System.out.print("url01"); //相当于其它逻辑 
                    msg.reply("success01");  //返回给result
                  }
                );
        vertx.eventBus().consumer(URL02, 
                msg -> {
                    System.out.println(msg.body());
                    System.out.print("url02"); //相当于其它逻辑 
                    msg.reply("success02");  //返回给result
                }
                );
    }
}