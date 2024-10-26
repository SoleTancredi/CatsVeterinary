package com.veterinaria.exclusive_cats.repository;

import com.veterinaria.exclusive_cats.entity.Gato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GatoRepository extends JpaRepository<Gato, Long> {
    Optional<Gato> findByNameAndAge(String name, int age);
}
