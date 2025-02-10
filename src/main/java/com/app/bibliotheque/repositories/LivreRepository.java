package com.app.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bibliotheque.models.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long>{

}
