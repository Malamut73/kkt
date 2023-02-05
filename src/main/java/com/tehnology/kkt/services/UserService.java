package com.tehnology.kkt.services;

import com.tehnology.kkt.configuration.GeneratePassword;
import com.tehnology.kkt.models.Product;
import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Role;
import com.tehnology.kkt.repositories.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final GeneratePassword generatePassword;
    private final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDAO.findByEmail(email);
    }

    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.getRoles().add(Role.Administrator);
        userDAO.save(user);
    }

    public List<User> findAllClients() {
        return userDAO.findUserByRoles(Role.Client);
    }

    public void saveClient(User user) {
        user.getRoles().add(Role.Client);
        userDAO.save(user);
    }

    public User findById(Long id) {
        return userDAO.getReferenceById(id);
    }


    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<User> findAllManagers() {
        return userDAO.findUserByRoles(Role.Manager);
    }

    public List<User> findAllAdministrator() {
        return userDAO.findUserByRoles(Role.Administrator);
    }

    public void editStaff(User user) {
        userDAO.save(user);
    }

    public void createManager(User user) {
        String pass = generatePassword.generateSecurePassword();
        user.setPassword(passwordEncoder.encode(pass));
        userDAO.save(user);
        mailService.sendPassword(pass, user);
    }

    public List<User> findAllByName(String name) {
        return userDAO.findAllByName(name);
    }

    public List<User> findAllClientsBy(String text, String search) {
        if (text.equals("")) return userDAO.findUserByRoles(Role.Client);
        switch (search){
            case "lastName" :
                return userDAO.findAllByRolesAndName(Role.Client, text);
            default:
                return userDAO.findAllByRolesAndLastName(Role.Client, text);
        }
    }

    public User findClientBy(String lastName, String name, String nameOfOrganization) {
        return userDAO.findUserByLastNameAndNameAndNameOfOrganization(
                lastName, name, nameOfOrganization
        );
    }
}


