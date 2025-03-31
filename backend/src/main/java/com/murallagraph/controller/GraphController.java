package com.murallagraph.controller;

import com.murallagraph.entity.Graph;
import com.murallagraph.service.GraphService;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.murallagraph.service.GeoJsonSender;

import java.io.*;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/graphs")
@CrossOrigin(origins = "*")
public class GraphController {


    
    @Autowired
    private GeoJsonSender geoJsonSender;

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping
    public ResponseEntity<Graph> saveGraph(@RequestBody Graph graph) {
        Graph savedGraph = graphService.saveGraph(graph);
        URI location = URI.create("/graphs/" + savedGraph.getName());
        return ResponseEntity.created(location).body(savedGraph);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Graph> getGraphByName(@PathVariable String name) {
        return graphService.getGraphByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Graph>> getAllGraphs() {
        List<Graph> graphs = graphService.getAllGraphs();
        return ResponseEntity.ok(graphs);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<InputStreamResource> downloadGeoJson(@PathVariable String id) throws IOException {

        Graph graph = graphService.getGraphById(id)
                .orElseThrow(() -> new RuntimeException("Graph not found"));


        String geoJsonString = graph.getGeoJsonString();
        if (geoJsonString == null || geoJsonString.isEmpty()) {
            throw new RuntimeException("GeoJSON data is empty");
        }


        File tempFile = File.createTempFile(graph.getName().replaceAll("\\s+", "_"), ".geojson");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(geoJsonString);
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(tempFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + graph.getName() + ".geojson\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(tempFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/{id}/send-geojson")
    public String sendGeoJson(@PathVariable String id) {

        Graph graph = graphService.getGraphById(id)
                .orElseThrow(() -> new RuntimeException("Grafo no encontrado con la ID proporcionada."));
        if (graph == null) {
            return "Grafo no encontrado con la ID proporcionada.";
        }
        String geoJsonString = graph.getGeoJsonString();
        return geoJsonSender.sendGeoJson(geoJsonString);
    }
}
