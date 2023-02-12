package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Comment;
import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.repositories.RequestDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestDAO requestDAO;

    public void saveRequest(Request request) {
        requestDAO.save(request);
    }

    public Request findById(Long requestid) {
//        Request request = requestDAO.getReferenceById(requestid);
//        List<Comment> comments = request.getComments().stream()
//                .sorted(Comparator.comparing(Comment::getDateOfCreated))
//                .collect(Collectors.toList());
//        request.setComments(comments);
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
}
