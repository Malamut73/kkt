package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimCardDAO extends JpaRepository<SimCard, Long> {
}
