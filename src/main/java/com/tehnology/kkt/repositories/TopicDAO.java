package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.extraclasses.firdirectory.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicDAO extends JpaRepository<Topic, Long> {
}
