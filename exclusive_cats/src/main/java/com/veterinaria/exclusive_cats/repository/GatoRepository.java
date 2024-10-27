package com.veterinaria.exclusive_cats.repository;

import com.veterinaria.exclusive_cats.entity.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GatoRepository extends JpaRepository<Gato, Long> {
    Optional<Gato> findByNombreAndEdad(String name, int age);
}
