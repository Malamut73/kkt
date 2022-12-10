package com.tehnology.kkt.repositories;


import com.tehnology.kkt.models.extraclasses.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceDAO extends JpaRepository<Maintenance, Long> {
}
