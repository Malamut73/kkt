package com.tehnology.kkt.services;

import com.tehnology.kkt.models.catalog.TypeOfActivity;
import com.tehnology.kkt.repositories.TypeOfActivityDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfActivityService {

    private final TypeOfActivityDAO typeOfActivityDAO;


    public List<TypeOfActivity> findAll() {
        return typeOfActivityDAO.findAll();
    }

    public void save(TypeOfActivity typeOfActivity) {
        typeOfActivityDAO.save(typeOfActivity);
    }

    public TypeOfActivity findById(Long id) {
        return typeOfActivityDAO.getReferenceById(id);
    }
}
