package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationDAO extends JpaRepository<Operation, Long> {

}
