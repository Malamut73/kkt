package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Operation;
import com.tehnology.kkt.repositories.OperationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationDAO operationDAO;


    public void save(Operation operation) {
        operationDAO.save(operation);
    }
}
