package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestDAO extends JpaRepository<Request, Long> {
    List<Request> findAllByActiveOrderByDateOfCreatedAsc(boolean b);

    List<Request> findAllByOrderByDateOfCreatedAsc();
}
