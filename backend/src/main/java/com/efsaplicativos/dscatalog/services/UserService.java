package com.efsaplicativos.dscatalog.services;

import com.efsaplicativos.dscatalog.dtos.RoleDto;
import com.efsaplicativos.dscatalog.dtos.UserDto;
import com.efsaplicativos.dscatalog.dtos.UserInsertDto;
import com.efsaplicativos.dscatalog.dtos.UserUpdateDto;
import com.efsaplicativos.dscatalog.entities.Role;
import com.efsaplicativos.dscatalog.entities.User;
import com.efsaplicativos.dscatalog.exceptions.DatabaseException;
import com.efsaplicativos.dscatalog.exceptions.ResourceNotFoundException;
import com.efsaplicativos.dscatalog.projections.UserDetailsProjection;
import com.efsaplicativos.dscatalog.repositories.RoleRepository;
import com.efsaplicativos.dscatalog.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public UserDto findMe() {
        User entity = authService.authenticated();
        return new UserDto(entity);
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> entities = repository.searchAll(pageable);
        return entities.map(UserDto::new);
    }

    @Transactional
    public UserDto save(UserInsertDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        entity.getRoles().clear();
        Role roleOperator = roleRepository.findByAuthority("ROLE_OPERATOR");
        entity.getRoles().add(roleOperator);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDto(entity);
    }

    @Transactional
    public UserDto update(Long id, UserUpdateDto dto) {
        try {
            User entity = repository.getReferenceById(id);
            BeanUtils.copyProperties(dto, entity);
            entity.setId(id);
            entity = repository.save(entity);
            return new UserDto(entity);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
