package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactPersonDAO extends JpaRepository<ContactPerson, Long> {
    ContactPerson findByNameOfContactAndPhoneOfContact(String nameOfContact, String phoneOfContact);
}
