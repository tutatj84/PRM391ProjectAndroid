package com.example.myapplication.model;

public class OrderItem {

    private int itemId;
    private int proId;
    private int quantity;
    private double price;
    private boolean isDelete;

    public OrderItem() {
    }

    public OrderItem(int proId, int quantity, double price, boolean isDelete) {
        this.proId = proId;
        this.quantity = quantity;
        this.price = price;
        this.isDelete = isDelete;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", proId=" + proId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
