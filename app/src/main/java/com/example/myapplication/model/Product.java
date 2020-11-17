package com.example.myapplication.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int productID;
    private String image;
    private String name;
    private int remainQuantity;
    private String type;
    private String content;
    private Long price;

    public Product() {
    }

    public Product(String image, String name, int remainQuantity, String type, String content, Long price) {
        this.image = image;
        this.name = name;
        this.remainQuantity = remainQuantity;
        this.type = type;
        this.content = content;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(int remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
