package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionDAO extends JpaRepository<Description, Long> {
}
