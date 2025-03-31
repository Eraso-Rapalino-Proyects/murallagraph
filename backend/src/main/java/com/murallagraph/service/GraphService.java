package com.murallagraph.service;

import com.murallagraph.entity.Graph;
import com.murallagraph.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraphService {

    @Autowired
    private GraphRepository graphRepository;

    // Guardar o actualizar un grafo
    public Graph saveGraph(Graph graph) {
        graph.updateGeoJson();
        return graphRepository.save(graph);
    }

    // Obtener un grafo por ID
    public Optional<Graph> getGraphById(String id) {
        return graphRepository.findById(id);
    }

    // Eliminar un grafo por ID
    public void deleteGraphById(String id) {
        graphRepository.deleteById(id);
    }

    public Optional<Graph> getGraphByName(String name){
        return graphRepository.findByName(name);
    }

    public List<Graph> getAllGraphs(){
        return graphRepository.findAll();
    }
}
