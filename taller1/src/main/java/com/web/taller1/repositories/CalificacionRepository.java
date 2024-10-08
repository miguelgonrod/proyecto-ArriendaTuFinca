package com.web.taller1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.taller1.entities.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}
