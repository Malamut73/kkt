package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Description;
import com.tehnology.kkt.repositories.DescriptionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescriptionService {

    private final DescriptionDAO descriptionDAO;


    public List<Description> findAll() {
        return descriptionDAO.findAll();
    }

    public void saveDescription(Description description) {
        descriptionDAO.save(description);
    }

    public Description findById(Long id) {
        return descriptionDAO.getReferenceById(id);
    }
}
