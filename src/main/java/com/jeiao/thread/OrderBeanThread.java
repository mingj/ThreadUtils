package com.jeiao.thread;

import com.jeiao.ThreadMain;
import com.jeiao.bean.OrderBean;
import com.jeiao.common.MemoryCache;
import com.jeiao.queue.OrderBeanQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;


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
        try {
            while (!OrderBeanQueue.isEmpty()) {
                OrderBean orderBean = OrderBeanQueue.getOrder();

                if (orderBean == null) {
                    continue;
                }


                //测试
                //int i = 1 / 0;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    ThreadMain.threadStatus++;
                    e.printStackTrace();
                    throw new Exception(e);
                }



                logger.info(threadName);
                logger.info(countDownLatch.getCount());

                String orderId = orderBean.getOrderId();

                //do something

                MemoryCache.orderBeanMap.put(orderId, orderBean);
            }
        } catch (Exception e) {
            ThreadMain.threadStatus++;
            throw new RuntimeException("线程处理异常，线程退出");

        }finally {
            countDownLatch.countDown();
            logger.info(countDownLatch.getCount());
        }
    }
}
