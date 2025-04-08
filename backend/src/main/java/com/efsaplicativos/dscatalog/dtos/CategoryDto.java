package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.Category;
import com.efsaplicativos.dscatalog.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDto {

    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CategoryDto(Category category) {
        id = category.getId();
        name = category.getName();
        createdAt = category.getCreatedAt();
        updatedAt = category.getUpdatedAt();
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
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
