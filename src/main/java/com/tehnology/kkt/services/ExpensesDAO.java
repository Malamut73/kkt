package com.tehnology.kkt.services;

import com.tehnology.kkt.models.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesDAO extends JpaRepository<Expenses, Long> {
}
