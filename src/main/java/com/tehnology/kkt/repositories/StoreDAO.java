package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDAO extends JpaRepository<Store, Long> {
}
