package com.jeiao.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class GenSequeueUtil {

    private static long index = 1000000000;

    public static synchronized  String getSeqId() {
        index++;
        return StringUtils.leftPad(String.valueOf(index), 8, "");
    }

}
