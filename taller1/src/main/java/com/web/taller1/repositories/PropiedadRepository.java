package com.web.taller1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.web.taller1.entities.Propiedad;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long>, JpaSpecificationExecutor<Propiedad> {
    List<Propiedad> findByUsuarioId(Long usuarioId);  // Reemplaza arrendatarioId por usuarioId
}