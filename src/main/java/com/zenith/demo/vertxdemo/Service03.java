package com.zenith.demo.vertxdemo;


import io.vertx.core.AbstractVerticle;

public class Service03 extends AbstractVerticle{

    public static final String URL03="VERTX_HELLO_SERVER03";
    
    public void start(){
        vertx.eventBus().consumer(URL03,   //处理总线发送过来的相应的事件
                msg -> {                   //msg消息发送过来的消息
                    System.out.println(msg.body()); //处理请求消息
                    System.out.print("url03"); //相当于其它逻辑 
                    msg.reply("success03");  //对事件进行返回结果，相当于事件中的result
                  }
                );
    }
}
