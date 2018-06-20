package com.zenith.demo.rocketmqtrans;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

public class TestProducer {
	 public static void main(String[] args) throws MQClientException, InterruptedException {
	        String group_name = "transaction_producer";

	        final TransactionMQProducer producer = new TransactionMQProducer(group_name);

	        producer.setNamesrvAddr("192.168.3.6:9876;192.168.3.7:9876");
	        producer.setCheckRequestHoldMax(200);
	        producer.setCheckThreadPoolMaxSize(20);
	        producer.setCheckThreadPoolMinSize(5);
	        producer.start();
	        //服务器回调producer,检查本地事务分支成功还是失败
	        producer.setTransactionCheckListener(new TransactionCheckListener() {

	            @Override
	            public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
	                System.out.println("state --" + new String(msg.getBody()));
	                return LocalTransactionState.COMMIT_MESSAGE;
	            }
	        });

	        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();

	        for(int i=0; i<2 ; i++) {
	            try {
	                Message msg = new Message("TopicTransaction","transaction" + i,"key",("hello " + i).getBytes());
	                SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, "tq");
	                System.out.println(sendResult);
	            }catch(Exception e) {
	                e.printStackTrace();
	            }
	        }
	        Thread.sleep(3000);
	        producer.shutdown();
	    }
}
