package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.Category;
import com.efsaplicativos.dscatalog.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDto {

    private Long id;
    private String name;

    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto() {
    }

    public ProductDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        for (Category category : product.getCategories()) {
            categories.add(new CategoryDto(category));
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

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
