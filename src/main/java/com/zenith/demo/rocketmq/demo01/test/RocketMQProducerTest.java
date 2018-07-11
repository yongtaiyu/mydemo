package com.zenith.demo.rocketmq.demo01.test;


import com.alibaba.rocketmq.common.message.Message;
import com.zenith.demo.rocketmq.demo01.RocketMQProducer;

/**
 * Created by lance on 2017/2/10.
 */
public class RocketMQProducerTest {

    public static void main(String[] args) {
    	String mqNameServer = "192.168.3.6:9876;192.168.3.7:9876";
    	String mqTopics = "MQ-MSG-TOPICS-TEST";

        String producerMqGroupName = "PRODUCER-MQ-GROUP";
        RocketMQProducer mqProducer = new RocketMQProducer(mqNameServer, producerMqGroupName, mqTopics);
        mqProducer.init();

        for (int i = 0; i < 10000; i++) {
            Message message = new Message();
            // 设置代表消息的业务关键属性，请尽可能全局唯一。
            // 以方便您在无法正常收到消息情况下，可通过KEY查询消息并补发。
            // 注意：不设置也不会影响消息正常收发
            message.setKeys("todotask");
            // 消息标签
            message.setTags("todotask");
            message.setBody(("I send message to RocketMQ " + i).getBytes());
            mqProducer.send(message);
        }
        // 在应用退出前，销毁Producer对象
        // 注意：如果不销毁也没有问题
        mqProducer.shutdown();
    }

}
