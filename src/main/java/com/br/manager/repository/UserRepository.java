package com.br.manager.repository;

import com.br.manager.model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDB, Long> {

    Boolean existsByEmail(String email);

    Optional<UserDB> findByEmail(String email);

}