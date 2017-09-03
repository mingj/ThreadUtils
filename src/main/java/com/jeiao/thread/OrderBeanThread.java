package com.jeiao.thread;

import com.jeiao.bean.OrderBean;
import com.jeiao.common.MemoryCache;
import com.jeiao.queue.OrderBeanQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.CountDownLatch;


/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class OrderBeanThread implements Runnable{

    private static Logger logger = LogManager.getLogger();



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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info(threadName);
            logger.info(countDownLatch.getCount());

            String orderId = orderBean.getOrderId();

            //do something

            MemoryCache.orderBeanMap.put(orderId, orderBean);

        }
        countDownLatch.countDown();
        logger.info(countDownLatch.getCount());
    }
}
