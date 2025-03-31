package com.murallagraph.repository;

import com.murallagraph.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Buscar usuario por nombre de usuario
    User findByUsername(String username);

    // Buscar usuario por correo
    User findByEmail(String email);

    // Obtener todos los usuarios con un rol específico
    List<User> findByRole(String role);

    // Contar cuántos usuarios tienen un rol específico
    long countByRole(String role);

    // Eliminar usuario por nombre de usuario
    void deleteByUsername(String username);

  
    // Buscar usuarios creados después de una fecha específica
    //List<User> findByCreatedAtAfter(LocalDateTime dateTime);
}