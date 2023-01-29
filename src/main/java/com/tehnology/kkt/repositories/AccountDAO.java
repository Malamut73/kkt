package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO extends JpaRepository<Account, Long> {
}
