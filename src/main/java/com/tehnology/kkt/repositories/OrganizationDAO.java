package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.extraclasses.firdirectory.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationDAO extends JpaRepository<Organization, Long> {
}