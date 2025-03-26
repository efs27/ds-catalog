package com.efsaplicativos.dscatalog.repositories;

import com.efsaplicativos.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
