package com.murallagraph.service;

import com.murallagraph.entity.StoredGraph;
import com.murallagraph.repository.StoredGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StoredGraphService {

    @Autowired
    private StoredGraphRepository storedGraphRepository;

    // Guardar o actualizar un grafo almacenado
    public StoredGraph saveStoredGraph(StoredGraph storedGraph) {
        return storedGraphRepository.save(storedGraph);
    }

    // Obtener un grafo almacenado por ID
    public Optional<StoredGraph> getStoredGraphById(String id) {
        return storedGraphRepository.findById(id);
    }

    // Eliminar un grafo almacenado por ID
    public void deleteStoredGraphById(String id) {
        storedGraphRepository.deleteById(id);
    }

    // Eliminar un grafo almacenado por nombre
    public void deleteStoredGraphByName(String name) {
        storedGraphRepository.deleteByName(name);
    }

    // Eliminar un grafo almacenado por ruta
    public void deleteStoredGraphByPath(String path) {
        storedGraphRepository.deleteByFilePath(path);
    }
}
