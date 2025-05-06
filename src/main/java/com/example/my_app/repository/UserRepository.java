package com.example.my_app.repository;

import com.example.my_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {

    boolean existsByNumberPhone(String numberPhone);

    boolean existsByEmail(String email);

    List<User>findByFirstnameStartingWithOrderByFirstnameDesc(String firstname);

    Optional<User> findByNumberPhone(String numberPhone);

    Optional<User>findByEmail(String email);
}
