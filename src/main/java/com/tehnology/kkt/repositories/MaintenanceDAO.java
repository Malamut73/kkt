package com.tehnology.kkt.repositories;


import com.tehnology.kkt.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceDAO extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findAllByOrderByDayEndDesc();
}
