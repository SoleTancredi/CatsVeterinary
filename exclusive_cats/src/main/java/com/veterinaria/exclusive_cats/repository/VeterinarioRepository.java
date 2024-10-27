package com.veterinaria.exclusive_cats.repository;

import com.veterinaria.exclusive_cats.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional<Veterinario> findByNombreAndApellido(String name, String lastname);
}
