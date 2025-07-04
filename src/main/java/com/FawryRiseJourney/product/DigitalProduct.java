package com.FawryRiseJourney.product;

import java.time.LocalDate;

public class DigitalProduct extends Product {
    String DigitalCode;

    public DigitalProduct(String productName, double price, int quantity, LocalDate ExpireDate, String DigitalCode) {
        super(productName, price, quantity, ExpireDate);
        this.DigitalCode = DigitalCode;
    }

    public DigitalProduct(String productName, double price, int quantity, String DigitalCode) {
        super(productName, price, quantity);
        this.DigitalCode = DigitalCode;

    }

    @Override
    String getProductType() {
        return "Digital Product";
    }
}
