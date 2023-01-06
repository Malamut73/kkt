package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.firdirectory.Internet;
import com.tehnology.kkt.repositories.InternetDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternetService {

    private final InternetDAO internetDAO;

    public List<Internet> findAll() {
        return internetDAO.findAll();
    }

    public void save(Internet internet) {
        internetDAO.save(internet);
    }

    public void deleteById(Long internetid) {
        internetDAO.deleteById(internetid);
    }

    public Internet findById(Long id) {
        return internetDAO.getReferenceById(id);
    }
}
