package com.zenith.demo.vertxdemo;


import io.vertx.core.AbstractVerticle;

public class Service03 extends AbstractVerticle{

    public static final String URL03="VERTX_HELLO_SERVER03";
    
    public void start(){
        vertx.eventBus().consumer(URL03,   //�������߷��͹�������Ӧ���¼�
                msg -> {                   //msg��Ϣ���͹�������Ϣ
                    System.out.println(msg.body()); //����������Ϣ
                    System.out.print("url03"); //�൱�������߼� 
                    msg.reply("success03");  //���¼����з��ؽ�����൱���¼��е�result
                  }
                );
    }
}
