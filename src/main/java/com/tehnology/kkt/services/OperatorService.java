package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Operator;
import com.tehnology.kkt.repositories.OperatorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorDAO operatorDAO;


    public List<Operator> findAll() {
        return operatorDAO.findAll();
    }

    public void save(Operator operator) {
        operatorDAO.save(operator);
    }

    public Operator findById(Long id) {
        return operatorDAO.getReferenceById(id);
    }
}

