package com.zenith.demo.rocketmq.test;


import com.alibaba.rocketmq.common.message.Message;
import com.zenith.demo.rocketmq.RocketMQProducer;

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
            message.setKeys("todotask");
            message.setBody(("I send message to RocketMQ " + i).getBytes());
            mqProducer.send(message);
        }
    }

}
