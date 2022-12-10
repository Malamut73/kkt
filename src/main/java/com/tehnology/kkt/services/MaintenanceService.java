package com.tehnology.kkt.services;

import com.tehnology.kkt.models.extraclasses.Maintenance;
import com.tehnology.kkt.repositories.MaintenanceDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceDAO maintenanceDAO;

    public void save(Maintenance maintenance) {
        maintenanceDAO.save(maintenance);
    }
}
