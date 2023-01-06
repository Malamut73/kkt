package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.extraclasses.firdirectory.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripDAO extends JpaRepository<Trip, Long> {
}
