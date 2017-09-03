package com.jeiao.thread;

import com.jeiao.bean.OrderBean;
import com.jeiao.common.MemoryCache;
import com.jeiao.queue.OrderBeanQueue;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class OrderBeanThread implements Runnable{

    private String threadName;

    private CountDownLatch countDownLatch;


    public OrderBeanThread(String threadName, CountDownLatch countDownLatch) {
        this.threadName = threadName;
        this.countDownLatch = countDownLatch;
    }

    public void run() {

        while (!OrderBeanQueue.isEmpty()) {
            OrderBean orderBean = OrderBeanQueue.getOrder();

            if (orderBean == null) {
                continue;
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName);

            String orderId = orderBean.getOrderId();

            //do something

            MemoryCache.orderBeanMap.put(orderId, orderBean);

        }
        countDownLatch.countDown();
    }
}
