package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionDAO extends JpaRepository<Description, Long> {
}
