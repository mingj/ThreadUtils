package com.jeiao;

import com.jeiao.bean.OrderBean;
import com.jeiao.common.MemoryCache;
import com.jeiao.queue.OrderBeanQueue;
import com.jeiao.thread.OrderBeanThread;
import com.jeiao.utils.GenSequeueUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class ThreadMain {

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 20; i++) {
            OrderBean bean = new OrderBean();
            String seqId = GenSequeueUtil.getSeqId();
            bean.setOrderId(seqId);
            bean.setOrderName(seqId);
            OrderBeanQueue.addOrder(bean);
        }


        int count = 10;

        CountDownLatch countDownLatch = new CountDownLatch(count);

        ExecutorService executorService = Executors.newFixedThreadPool(25);


        for (int i=0;i<count;i++) {
            OrderBeanThread thread = new OrderBeanThread("thread"+i,countDownLatch);
            executorService.submit(thread);
        }

        countDownLatch.await();
        executorService.shutdown();


        System.out.println(MemoryCache.orderBeanMap.size());

    }


}