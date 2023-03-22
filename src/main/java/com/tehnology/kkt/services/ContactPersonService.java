package com.tehnology.kkt.services;

import com.tehnology.kkt.models.ContactPerson;
import com.tehnology.kkt.repositories.ContactPersonDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactPersonService {

    private final ContactPersonDAO contactPersonDAO;

    public void save(ContactPerson contactPerson) {
        contactPersonDAO.save(contactPerson);
    }

    public ContactPerson findBy(String nameOfContact, String phoneOfContact) {
        return contactPersonDAO.findByNameOfContactAndPhoneOfContact(nameOfContact, phoneOfContact);
    }

    public void delete(ContactPerson contactPerson) {
        contactPersonDAO.delete(contactPerson);
    }
}
