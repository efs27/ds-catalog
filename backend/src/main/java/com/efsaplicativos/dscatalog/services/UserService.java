package com.efsaplicativos.dscatalog.services;

import com.efsaplicativos.dscatalog.dtos.UserDto;
import com.efsaplicativos.dscatalog.entities.User;
import com.efsaplicativos.dscatalog.exceptions.DatabaseException;
import com.efsaplicativos.dscatalog.exceptions.ResourceNotFoundException;
import com.efsaplicativos.dscatalog.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDto(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(UserDto::new);
    }

    @Transactional
    public UserDto save(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user = repository.save(user);
        return new UserDto(user);
    }

    @Transactional
    public UserDto update(Long id, UserDto dto) {
        try {
            User user = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, user);
            user = repository.save(user);
            return new UserDto(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referêncial");
        }
    }
}
