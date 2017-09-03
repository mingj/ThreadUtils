package com.jeiao.common;

import com.jeiao.bean.OrderBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class MemoryCache {

    public static Map<String, OrderBean> orderBeanMap = new ConcurrentHashMap<String, OrderBean>();

}
