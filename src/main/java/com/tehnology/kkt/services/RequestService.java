package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.repositories.RequestDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestDAO requestDAO;

    public void saveRequest(Request request) {
        requestDAO.save(request);
    }
}
