package com.efsaplicativos.dscatalog.dtos;

import com.efsaplicativos.dscatalog.entities.Category;
import com.efsaplicativos.dscatalog.entities.Product;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDto {

    private Long id;
    @Size(min = 5,max = 60,message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo nome obrigatório")
    private String name;
    @NotBlank(message = "Descrição é obrigatório")
    private String description;
    @Positive(message = "Preço deve ser positivo")
    private Double price;
    private String imgUrl;
    @PastOrPresent(message = "Data não pode ser futura")
    private Instant date;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
        date = product.getDate();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
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
