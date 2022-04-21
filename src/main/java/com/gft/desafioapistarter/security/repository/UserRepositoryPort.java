package com.gft.desafioapistarter.security.repository;

import com.gft.desafioapistarter.security.entity.User;

import java.util.Optional;


public interface UserRepositoryPort {

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    User save(User user);

}
