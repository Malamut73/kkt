package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Trip;
import com.tehnology.kkt.repositories.TripDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public void delete(Trip trip) {
        tripDAO.delete(trip);
    }

    public void deleteById(Long id) {
        tripDAO.deleteById(id);
    }

    public void deleteAll(Set<Trip> trips) {
        tripDAO.deleteAll(trips);
    }
}
