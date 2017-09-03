package com.jeiao.queue;

import com.jeiao.bean.OrderBean;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class OrderBeanQueue {

    private static ConcurrentLinkedQueue<OrderBean> queue = new ConcurrentLinkedQueue<OrderBean>();


    private static boolean running = false;


    public static void addOrder(OrderBean orderBean) {
        queue.add(orderBean);
    }

    public static synchronized OrderBean getOrder() {
        return queue.poll();
    }

    public static int size() {
        return queue.size();
    }

    public static void clear() {
        queue.clear();
    }


    public static boolean isEmpty() {
        return queue.isEmpty();
    }


    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        OrderBeanQueue.running = running;
    }
}
