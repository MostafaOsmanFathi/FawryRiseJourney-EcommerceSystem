package com.FawryRiseJourney.product;

import java.time.LocalDate;

public class ShippableProduct extends Product {
    private double weight;
    private Shippable shippable;

    public ShippableProduct(String name, double price, int quantity, LocalDate expireDate, double weight) {
        super(name, price, quantity, expireDate);
        this.weight = weight;
    }

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    String getProductType() {
        return "Shippable Product";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double shippingCost(){
        return shippable.CalculateShippingCost(getWeight());
    }

}
