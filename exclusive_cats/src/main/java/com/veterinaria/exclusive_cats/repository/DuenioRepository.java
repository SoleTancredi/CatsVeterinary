package com.veterinaria.exclusive_cats.repository;

import com.veterinaria.exclusive_cats.entity.Duenio;
import com.veterinaria.exclusive_cats.entity.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Long> {
    Optional<Duenio> findByNombreAndApellido(String name, String lastname);
}
