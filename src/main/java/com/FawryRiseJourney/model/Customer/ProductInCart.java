package com.FawryRiseJourney.model.Customer;

import com.FawryRiseJourney.model.product.Product;
import com.FawryRiseJourney.model.product.ShippableProduct;

public class ProductInCart {
    private int numOfSelectedQuantity;
    private final Product product;


    public ProductInCart(Product product, int numOfSelectedQuantity) {
        this.product = product;
        this.numOfSelectedQuantity = numOfSelectedQuantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * numOfSelectedQuantity;
    }

    public double getTotalWeight() {
        if (product instanceof ShippableProduct shippableProduct) {
            return shippableProduct.getWeight() * numOfSelectedQuantity;
        }
        return 0.0;
    }

    public double getTotalShippingCost() {
        if (product instanceof ShippableProduct shippableProduct) {
            return shippableProduct.shippingCost() * numOfSelectedQuantity;
        }
        return 0.0;
    }

    public double getNumOfSelectedQuantity() {
        return numOfSelectedQuantity;
    }

    public void setNumOfSelectedQuantity(int numOfSelectedQuantity) {
        if (numOfSelectedQuantity < 0) {
            this.numOfSelectedQuantity = 0;
        } else if (numOfSelectedQuantity > product.getQuantity()) {
            this.numOfSelectedQuantity = product.getQuantity();
        } else
            this.numOfSelectedQuantity = numOfSelectedQuantity;
    }

    public boolean applyTransaction() {
        if (numOfSelectedQuantity > product.getQuantity()) {
            return false;
        }
        product.setQuantity(product.getQuantity() - numOfSelectedQuantity);
        return true;
    }

    public Product getProduct() {
        return product;
    }


    @Override
    public String toString() {
        if (product instanceof ShippableProduct shippableProduct) {
            return shippableProduct.getProductType() + "\t" + numOfSelectedQuantity + "\t" + shippableProduct.getWeight() + "kg \t" + getTotalWeight() +
                    "\n total price without shipping fees:" + getTotalPrice() + "\n total shipping fee:" + getTotalShippingCost() +
                    "\ntotal price for: " + shippableProduct.getProductName() + " is :" + getTotalPrice() + getTotalShippingCost();

        }
        return product.getProductType() + "\t" + numOfSelectedQuantity +
                "\n total price :" + getTotalPrice();
    }
}
