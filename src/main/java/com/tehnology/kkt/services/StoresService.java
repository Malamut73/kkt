package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Store;
import com.tehnology.kkt.repositories.StoreDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoresService {

    private final StoreDAO storeDAO;

    public List<Store> findAll() {
        return storeDAO.findAll();
    }

    public void save(Store store) {
        storeDAO.save(store);
    }
}
