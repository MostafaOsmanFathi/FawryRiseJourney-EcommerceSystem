package com.FawryRiseJourney.model.product;

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

    public String getDigitalCode() {
        return DigitalCode;
    }

    public void setDigitalCode(String digitalCode) {
        DigitalCode = digitalCode;
    }

    @Override
    public String getProductType() {
        return "Digital Product";
    }
}
