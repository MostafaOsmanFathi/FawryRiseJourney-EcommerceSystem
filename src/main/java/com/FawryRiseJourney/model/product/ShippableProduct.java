package com.FawryRiseJourney.model.product;

import java.time.LocalDate;

public class ShippableProduct extends Product {
    private double weight;
    private Shippable shippable;


    public ShippableProduct(String name, double price, int quantity, LocalDate expireDate, double weight, Shippable shippable) {
        super(name, price, quantity, expireDate);
        this.weight = weight;
        this.shippable = shippable;
    }

    public ShippableProduct(String name, double price, int quantity, double weight, Shippable shippable) {
        super(name, price, quantity);
        this.weight = weight;
        this.shippable = shippable;
    }

    @Override
    public String getProductType() {
        return "Shippable Product";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double shippingCost() {
        return shippable.CalculateShippingCost(getWeight());
    }

}
