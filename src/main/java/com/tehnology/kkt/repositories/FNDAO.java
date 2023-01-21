package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.FN;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FNDAO extends JpaRepository<FN, Long> {
    List<FN> findAllByOrderByDayEndDesc();
}
