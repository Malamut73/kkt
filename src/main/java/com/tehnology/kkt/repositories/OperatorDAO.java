package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorDAO extends JpaRepository<Operator, Long> {
}
