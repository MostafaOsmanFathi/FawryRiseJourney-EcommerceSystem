package com.FawryRiseJourney.model.product;

public class PseudoShipping implements Shippable {
    int weightCostRate;

    public PseudoShipping(int weightCostRate) {
        this.weightCostRate = weightCostRate;
    }

    @Override
    public double CalculateShippingCost(double weight) {
        return weight * weightCostRate;
    }
}
