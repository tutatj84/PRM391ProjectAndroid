package com.example.myapplication.ui.entity;

public class OrderItem {

    private int itemId;
    private int proId;
    private int quantity;
    private double price;

    public OrderItem() {
    }

    public OrderItem(int proId, int quantity, double price) {
        this.proId = proId;
        this.quantity = quantity;
        this.price = price;
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
