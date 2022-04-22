package com.gft.desafioapistarter.repository;

import com.gft.desafioapistarter.model.Starters;
import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartersRepository extends JpaRepository<Starters, Long> {


}
