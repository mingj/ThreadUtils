package com.jeiao;

import com.jeiao.bean.OrderBean;
import com.jeiao.common.MemoryCache;
import com.jeiao.queue.OrderBeanQueue;
import com.jeiao.thread.OrderBeanThread;
import com.jeiao.utils.GenSequeueUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class ThreadMain {

    private static Logger logger = LogManager.getLogger();
    public static volatile int threadStatus = 0;

    public static void main(String[] args) throws Exception {


        for (int i = 0; i < 300; i++) {
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

        if (threadStatus > 0) {
            throw new Exception("线程异常，程序异常退出！");
        }

        logger.info("最后处理完成后的map数量："+MemoryCache.orderBeanMap.size());

    }


}
