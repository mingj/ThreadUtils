package com.jeiao.bean;

/**
 * Created by zhangmingjie on 2017/9/3.
 */
public class OrderBean {

    private String orderId;
    private String orderName;
    private String orderStatus;

    public OrderBean() {
    }

    public OrderBean(String orderId, String orderName, String orderStatus) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
