package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.repositories.RequestDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestDAO requestDAO;

    public void saveRequest(Request request) {
        requestDAO.save(request);
    }

    public Request findById(Long requestid) {
        return requestDAO.getReferenceById(requestid);
    }

    public void deleteRequest(Request request) {
        requestDAO.delete(request);
    }

    public List<Request> findAll() {
        return requestDAO.findAll();
    }
}
