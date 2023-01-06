package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.firdirectory.Topic;
import com.tehnology.kkt.repositories.TopicDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicDAO topicDAO;


    public List<Topic> findAll() {
        return topicDAO.findAll();
    }

    public void saveTopic(Topic topic) {
        topicDAO.save(topic);
    }

    public Topic findById(Long id) {
        return topicDAO.getReferenceById(id);
    }
}
