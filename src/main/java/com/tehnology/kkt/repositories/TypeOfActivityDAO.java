package com.tehnology.kkt.repositories;


import com.tehnology.kkt.controllers.directorycontrollers.TypeOfActivityController;
import com.tehnology.kkt.models.extraclasses.firdirectory.TypeOfActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOfActivityDAO extends JpaRepository<TypeOfActivity, Long> {
}
