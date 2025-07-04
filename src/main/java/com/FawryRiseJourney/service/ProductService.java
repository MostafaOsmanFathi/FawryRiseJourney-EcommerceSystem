package com.FawryRiseJourney.service;

import com.FawryRiseJourney.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService productService;
    private static List<Product> listOfProducts;

    private ProductService() {
        listOfProducts = new ArrayList<Product>();
    }

    public static ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public List<Product> getProductList() {
        return listOfProducts;
    }

    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

    public void productsOverview() {
        System.out.println("------Products Overview------");
        int idx = 0;
        for (Product product : listOfProducts) {
            System.out.println(Integer.toString(++idx) + "." + product);
        }
        System.out.println("-----------------------------");
    }
}
