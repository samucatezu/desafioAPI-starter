package com.gft.desafioapistarter.repository;

import com.gft.desafioapistarter.model.Starters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartersRepository extends JpaRepository<Starters, Long> {
}
