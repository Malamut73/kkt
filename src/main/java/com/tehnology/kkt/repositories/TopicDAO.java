package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicDAO extends JpaRepository<Topic, Long> {
}
