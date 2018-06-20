package com.zenith.demo.rocketmqtrans;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

/*
 * 执行本地事务，由客户端回调
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {

    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
        System.out.println("msg :" + new String(msg.getBody()));
        System.out.println("arg :" + arg);
        String tag = msg.getTags();
        if(tag.equals("transaction1")) {
            //这里有一个分阶段提交任务概念
            System.out.println("这里处理业务逻辑，如操作数据库，失败情况下进行ROLLBACK");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }

}