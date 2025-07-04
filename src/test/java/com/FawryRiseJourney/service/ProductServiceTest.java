package com.FawryRiseJourney.service;

import com.FawryRiseJourney.model.product.DigitalProduct;
import com.FawryRiseJourney.model.product.PseudoShipping;
import com.FawryRiseJourney.model.product.Shippable;
import com.FawryRiseJourney.model.product.ShippableProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductServiceTest {
    ProductService productService;
    Shippable pseudoShipping;

    @BeforeEach
    void setUp() {
        productService = ProductService.getProductService();
        productService.getProductList().clear();

        //Digital Product
        productService.addProduct(new DigitalProduct("Vodafone Scratch Card", 100.0, 5, "1234-1232-111-55"));
        productService.addProduct(new DigitalProduct("WE Scratch Card", 200, 10, "123674-1252-122-5522"));
        productService.addProduct(new DigitalProduct("Orange Scratch Card", 50.0, 13, "121334-134232-12211-55555"));


        pseudoShipping = new PseudoShipping(1);
        //Shippable Product
        productService.addProduct(new ShippableProduct("Biscites", 100.0, 10, LocalDate.of(2027, 3, 12), .25, pseudoShipping));
        productService.addProduct(new ShippableProduct("TV", 100.0, 3, 5, pseudoShipping));
        productService.addProduct(new ShippableProduct("Mobile", 100.0, 1, LocalDate.of(2027, 3, 12), 1, pseudoShipping));
    }

    @Test
    void getProductService() {
        assertNotNull(productService);
    }


    @Test
    void getAllProducts() {
        assertNotNull(productService.getProductList());
    }

    @Test
    void addProduct() {
        ShippableProduct product = new ShippableProduct("Laptop", 100.0, 3, 5, pseudoShipping);
        productService.addProduct(product);
        assert productService.getProductList().contains(product);
    }
}