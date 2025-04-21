package com.efsaplicativos.dscatalog.repositories;

import com.efsaplicativos.dscatalog.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT obj FROM Category obj JOIN FETCH obj.products")
    List<Category> searchAll();
}
