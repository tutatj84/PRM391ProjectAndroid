package com.example.myapplication.model;

public class OrderItem {

    private int itemId;
    private int proId;
    private int quantity;
    private Long price;
    private boolean isChoosing;

    public OrderItem() {
    }

    public OrderItem(int proId, int quantity, Long price, boolean isChoosing) {
        this.proId = proId;
        this.quantity = quantity;
        this.price = price;
        this.isChoosing = isChoosing;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isChoosing() {
        return isChoosing;
    }

    public void setChoosing(boolean choosing) {
        isChoosing = choosing;
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
