package com.zenith.demo.rocketmqtrans;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class TestConsumer {

	    public static void main(String[] args) throws MQClientException {
	        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_consumer");
	        //设置consumer第一次启动是从队列头部开始还是尾部开始消费，若非第一次启动，那么按照上次消费的位置继续消费
	        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
	        consumer.subscribe("TopicTransaction", "*");
	        //批量消费，一次消费多少条消息，默认为1条,最大情况能拿多少条不代表每次能拿这么多条
	        //consumer.setConsumeMessageBatchMaxSize(3);
	        //messageListenerOrderly 保证顺序消费，消费端接收的是同一个队列的消息，避免多线程时顺序错乱
	        /*consumer.registerMessageListener(new MessageListenerOrderly() {

	            @Override
	            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> arg0, ConsumeOrderlyContext arg1) {

	                return null;
	            }
	        });*/
	        consumer.setConsumeThreadMax(10);
	        consumer.setConsumeThreadMin(10);
	        consumer.registerMessageListener(new MessageListenerConcurrently() {

	            @Override
	            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
	                //System.out.println(Thread.currentThread().getName() + "Receive: " + msgs);
	                //获取一次性消费多少条消息
	                //System.out.println("消息条数 ： " + msgs.size());
	                MessageExt msg1 = null;
	                try {
	                    for(MessageExt msg : msgs) {
	                        msg1 = msg;
	                        String topic = msg.getTopic();
	                        String msgbody = new String(msg.getBody(),"utf-8");
	                        String tag = msg.getTags();
	                        System.out.println("收到消息： " + "topic:" + topic + " tags:" + tag + " msg:" + msgbody);
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    //若已经重试了5次则不再重试
	                    if(msg1.getReconsumeTimes() == 5) {
	                        //此处记录日志操作。。。
	                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	                    }
	                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
	                }

	                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	            }
	        });
	        consumer.setNamesrvAddr("192.168.3.6:9876;192.168.3.7:9876");
	        consumer.start();
	        System.out.println("Consumer started...");
	    }

	}