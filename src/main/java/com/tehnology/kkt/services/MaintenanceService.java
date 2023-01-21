package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Maintenance;
import com.tehnology.kkt.repositories.MaintenanceDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceDAO maintenanceDAO;

    public void save(Maintenance maintenance) {
        maintenanceDAO.save(maintenance);
    }

    public Maintenance findById(Long maintenanceid) {
        return maintenanceDAO.getReferenceById(maintenanceid);
    }

    public void deleteById(Long maintenanceid) {
        maintenanceDAO.deleteById(maintenanceid);
    }

    public List<Maintenance> findAllByOrderByDayEndDesc() {
        return maintenanceDAO.findAllByOrderByDayEndDesc();
    }
}
