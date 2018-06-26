package com.zenith.demo.rocketmq.demo01.test;

import com.zenith.demo.rocketmq.demo01.RocketMQConsumer;
import com.zenith.demo.rocketmq.demo01.RocketMQListener;

/**
 * Created by lance on 2017/2/10.
 */
public class RocketMQConsumerTest {


    public static void main(String[] args) {
        String mqNameServer = "192.168.3.6:9876;192.168.3.7:9876";
        String mqTopics = "MQ-MSG-TOPICS-TEST";

        String consumerMqGroupName = "CONSUMER-MQ-GROUP";
        RocketMQListener mqListener = new RocketMQListener();
        RocketMQConsumer mqConsumer = new RocketMQConsumer(mqListener, mqNameServer, consumerMqGroupName, mqTopics);
        mqConsumer.init();

        try {
            Thread.sleep(1000 * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
