package com.efsaplicativos.dscatalog.services;

import com.efsaplicativos.dscatalog.dtos.CategoryDto;
import com.efsaplicativos.dscatalog.entities.Category;
import com.efsaplicativos.dscatalog.exceptions.DatabaseException;
import com.efsaplicativos.dscatalog.exceptions.ResourceNotFoundException;
import com.efsaplicativos.dscatalog.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        return new CategoryDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDto> findAll(Pageable pageable) {
        Page<Category> entities = repository.findAll(pageable);
        return entities.map(CategoryDto::new);
    }

    @Transactional
    public CategoryDto save(CategoryDto dto) {
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto dto) {
        try {
            Category entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity);
            entity.setId(id);
            entity = repository.save(entity);
            return new CategoryDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referêncial");
        }
    }
}
