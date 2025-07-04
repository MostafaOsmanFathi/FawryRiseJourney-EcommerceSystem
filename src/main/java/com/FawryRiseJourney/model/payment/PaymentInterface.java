package com.FawryRiseJourney.model.payment;

public interface PaymentInterface {
    boolean charge(double amount);
    boolean refund(double amount);
}
