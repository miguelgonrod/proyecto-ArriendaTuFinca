package com.web.taller1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.taller1.entities.UsuarioArrendatario;

@Repository
public interface UsuarioArrendatarioRepository extends JpaRepository<UsuarioArrendatario, Long> {
    // Aquí puedes agregar métodos de consulta personalizados si los necesitas
}
