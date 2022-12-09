package com.tehnology.kkt.services;

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
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        return user;
    }

    public boolean create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.getRoles().add(Role.Administrator);
        userDAO.save(user);
        return true;
    }

    public List<User> findAll() {
        List<User> clients = new ArrayList<>();
        List<User> allUsers = userDAO.findAll();
        for (User user :
                allUsers) {
            for (Role role :
                    user.getRoles()) {
                if (role.name().equals("Client")) {
                    clients.add(user);
                }
            }
        }

        return clients;
    }

    public void saveClient(User user) {
        user.getRoles().add(Role.Client);
        userDAO.save(user);
    }

    public User findById(Long id) {
        return userDAO.findById(id).orElseThrow();
    }
}


