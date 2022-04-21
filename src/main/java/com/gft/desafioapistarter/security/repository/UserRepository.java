package com.gft.desafioapistarter.security.repository;

import java.util.Optional;

import com.gft.desafioapistarter.security.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    Optional<User> findByEmail(String email);

}