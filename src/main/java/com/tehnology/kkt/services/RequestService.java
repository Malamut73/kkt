package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.repositories.RequestDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Request> findAllBy(String value) {
        switch (value){
            case "true":
                return requestDAO.findAllByActiveOrderByDateOfCreatedAsc(true);
            case "false":
                return requestDAO.findAllByActiveOrderByDateOfCreatedAsc(false);
            default:
                return requestDAO.findAllByOrderByDateOfCreatedAsc();
        }
    }

    public void setProductEquipment(Request request, String[] equip, Principal principal) {
        Set<String> equipments = new HashSet<>(Arrays.asList(equip));
        request.setProductEquipments(equipments);
        request.getComments().add(Comment.builder().user(principal.getName())
                .text("изменил список оборудования").build());
        requestDAO.save(request);

    }
}
