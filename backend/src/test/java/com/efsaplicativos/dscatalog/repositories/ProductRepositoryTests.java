package com.efsaplicativos.dscatalog.repositories;

import com.efsaplicativos.dscatalog.entities.Product;
import com.efsaplicativos.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProduct;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProduct = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product result = Factory.createProduct();
        result.setId(null);
        result = repository.save(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(countTotalProduct + 1, result.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {
        Optional<Product> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }
}
