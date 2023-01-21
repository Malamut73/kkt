package com.tehnology.kkt.services;

import com.tehnology.kkt.models.catalog.Organization;
import com.tehnology.kkt.repositories.OrganizationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationDAO organizationDAO;


    public List<Organization> findAll() {
        return organizationDAO.findAll();
    }

    public void save(Organization organization) {
        organizationDAO.save(organization);
    }

    public Organization findById(Long id) {
        return organizationDAO.getReferenceById(id);
    }
}
