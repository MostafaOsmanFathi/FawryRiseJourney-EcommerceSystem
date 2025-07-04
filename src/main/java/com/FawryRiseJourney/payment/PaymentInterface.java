package com.FawryRiseJourney.payment;

public interface PaymentInterface {
    boolean charge(double amount);
    boolean refund(double amount);
}
