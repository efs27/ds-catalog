package com.efsaplicativos.dscatalog.tests;

import com.efsaplicativos.dscatalog.dtos.ProductDto;
import com.efsaplicativos.dscatalog.entities.Category;
import com.efsaplicativos.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(new Category(2L, "Electronics"));
        return product;
    }

    public static ProductDto createProductDto() {
        Product product = createProduct();
        return new ProductDto(product);
    }
}
