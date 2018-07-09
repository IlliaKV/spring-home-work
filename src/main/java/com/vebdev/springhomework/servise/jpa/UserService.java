package com.vebdev.springhomework.servise.jpa;

import com.vebdev.springhomework.domain.Role;
import com.vebdev.springhomework.domain.User;
import com.vebdev.springhomework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void addNewUser(User user) {
//        Role userRole = roleService.getRoleByName("USER");
//        user.getRoles().add(userRole);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void setPassword(User user, String password) {
        String passwordHash = bCryptPasswordEncoder.encode(password);
        user.setPassword(passwordHash);
    }

    public boolean exists(long userId) {
        return userRepository.existsById(userId);
    }

    public User getById(long id){
        return userRepository.findById(id).get();
    }
}
