package com.web.taller1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.taller1.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Puedes agregar métodos de consulta personalizados si es necesario
    Usuario findByEmail(String email);
}
