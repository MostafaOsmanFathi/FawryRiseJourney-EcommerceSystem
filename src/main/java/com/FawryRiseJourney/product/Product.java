package com.FawryRiseJourney.product;

import java.time.LocalDate;

public abstract class Product {
    private String productName;
    private double price;
    private int quantity;
    private LocalDate ExpireDate; //if it was null it doesn't expire

    //products that expire
    public Product(String productName, double price, int quantity, LocalDate ExpireDate) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.ExpireDate = ExpireDate;
    }

    //products that not expire
    public Product(String productName, double price, int quantity) {
        this(productName, price, quantity, null);
    }

    abstract String getProductType();


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        ExpireDate = expireDate;
    }
}
