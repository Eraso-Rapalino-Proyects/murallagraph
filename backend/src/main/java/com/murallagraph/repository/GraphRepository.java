package com.murallagraph.repository;

import com.murallagraph.entity.Graph;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends MongoRepository<Graph, String> {

    // Buscar grafo por nombre
    Optional<Graph> findByName(String name);


    // Eliminar grafo por nombre
    void deleteByName(String name);

}
