package com.tehnology.kkt.services;

import com.tehnology.kkt.models.SimCard;
import com.tehnology.kkt.repositories.SimCardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimCardService {

    private final SimCardDAO simCardDAO;

    public void delete(SimCard simCard) {
        simCardDAO.delete(simCard);
    }
}
