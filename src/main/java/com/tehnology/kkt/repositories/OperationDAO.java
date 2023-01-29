package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Operation;
import com.tehnology.kkt.models.enums.Aim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationDAO extends JpaRepository<Operation, Long> {

    List<Operation> findOperationByAim(Aim aim);
}
