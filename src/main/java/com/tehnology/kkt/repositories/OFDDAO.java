package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.OFD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OFDDAO extends JpaRepository<OFD, Long> {
    List<OFD> findAllByOrderByDayEndDesc();
}
