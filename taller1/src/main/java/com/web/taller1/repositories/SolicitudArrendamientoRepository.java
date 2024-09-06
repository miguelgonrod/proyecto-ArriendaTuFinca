package com.web.taller1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.web.taller1.entities.SolicitudArrendamiento;


@Repository
public interface SolicitudArrendamientoRepository extends JpaRepository<SolicitudArrendamiento, Long> {
    public List<SolicitudArrendamiento> findByArrendador_Id(Long arrendadorId);
}