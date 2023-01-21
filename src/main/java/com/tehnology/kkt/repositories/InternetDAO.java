package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.catalog.Internet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternetDAO extends JpaRepository<Internet, Long> {
}
