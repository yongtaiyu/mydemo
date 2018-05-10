package com.zenith.demo.vertxdemo;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.cli.Option;
public class Service01 extends AbstractVerticle{
    public static final String URL01="VERTX_HELLO_SERVER01";
    public static final String URL02="VERTX_HELLO_SERVER02";
    
    public void start(){
        vertx.eventBus().consumer(URL01, 
                msg -> {
                    System.out.println(msg.body()); //����������Ϣ
                    System.out.print("url01"); //�൱�������߼� 
                    msg.reply("success01");  //���ظ�result
                  }
                );
        vertx.eventBus().consumer(URL02, 
                msg -> {
                    System.out.println(msg.body());
                    System.out.print("url02"); //�൱�������߼� 
                    msg.reply("success02");  //���ظ�result
                }
                );
    }
}