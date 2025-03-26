package com.efsaplicativos.dscatalog.repositories;

import com.efsaplicativos.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
