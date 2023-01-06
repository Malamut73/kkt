package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.firdirectory.Trip;
import com.tehnology.kkt.repositories.TripDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripDAO tripDAO;

    public Trip findById(Long tripid) {
        return tripDAO.getReferenceById(tripid);
    }

    public void save(Trip trip) {
        tripDAO.save(trip);
    }
}
