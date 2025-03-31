package com.murallagraph.repository;

import com.murallagraph.entity.StoredGraph;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredGraphRepository extends MongoRepository<StoredGraph, String> {

    // Buscar StoredGraph por nombre
    StoredGraph findByName(String name);

    // Eliminar StoredGraph por nombre
    void deleteByName(String name);

    // Eliminar StoredGraph por ruta
    void deleteByFilePath(String filePath);
}
