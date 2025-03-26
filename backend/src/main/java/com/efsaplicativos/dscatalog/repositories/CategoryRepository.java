package com.efsaplicativos.dscatalog.repositories;

import com.efsaplicativos.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
