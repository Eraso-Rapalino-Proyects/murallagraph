package com.murallagraph.service;

import com.murallagraph.entity.User;
import com.murallagraph.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Crear o actualizar un usuario
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Buscar usuario por ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Buscar usuario por nombre de usuario
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Buscar usuario por correo electrónico
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Obtener todos los usuarios con un rol específico
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    // Eliminar usuario por ID
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    // Eliminar usuario por nombre de usuario
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
