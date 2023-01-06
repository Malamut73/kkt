package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDAO extends JpaRepository<Request, Long> {
}
