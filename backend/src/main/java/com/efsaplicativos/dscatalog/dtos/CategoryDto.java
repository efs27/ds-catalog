package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.Category;
import com.efsaplicativos.dscatalog.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDto {

    private Long id;
    private String name;

    private List<ProductDto> products = new ArrayList<>();

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
        for (Product cat : category.getProducts()) {
            products.add(new ProductDto(cat));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CategoryDto that = (CategoryDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
