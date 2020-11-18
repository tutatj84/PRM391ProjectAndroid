package com.example.myapplication.model;

import java.util.List;


public class Order {
    private int orderId;
    private int cusId;
    private String status;
    private String orderDate;
    private String deliverDate;
    private String address;
    private double totalPrice;
    private List<OrderItem> items;

    public Order() {
    }

    public Order(int cusId, String status, String orderDate, String deliverDate, String address, double totalPrice) {
        this.cusId = cusId;
        this.status = status;
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
