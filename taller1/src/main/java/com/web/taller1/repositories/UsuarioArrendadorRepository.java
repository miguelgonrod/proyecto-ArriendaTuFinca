package com.web.taller1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.taller1.entities.UsuarioArrendador;

@Repository
public interface UsuarioArrendadorRepository extends JpaRepository<UsuarioArrendador, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si los necesitas
}
