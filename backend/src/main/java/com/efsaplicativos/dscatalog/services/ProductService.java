package com.efsaplicativos.dscatalog.services;

import com.efsaplicativos.dscatalog.dtos.ProductDto;
import com.efsaplicativos.dscatalog.entities.Product;
import com.efsaplicativos.dscatalog.exceptions.DatabaseException;
import com.efsaplicativos.dscatalog.exceptions.ResourceNotFoundException;
import com.efsaplicativos.dscatalog.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return new ProductDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<Product> entities = repository.findAll(pageable);
        return entities.map(ProductDto::new);
    }

    @Transactional
    public ProductDto save(ProductDto dto) {
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        entity = repository.save(entity);
        return new ProductDto(entity);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto dto) {
        try {
            Product entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity);
            entity.setId(id);
            entity = repository.save(entity);
            return new ProductDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referêncial");
        }
    }
}
