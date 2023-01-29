package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Operation;
import com.tehnology.kkt.models.enums.Aim;
import com.tehnology.kkt.repositories.OperationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsService {

    private final OperationDAO operationDAO;

    public List<Operation> findOperationByAim(Aim aim) {
       return operationDAO.findOperationByAim(aim);
    }

    public void save(Operation operation) {
        operationDAO.save(operation);
    }
}
