package com.murallagraph.controller;

import com.murallagraph.entity.Graph;
import com.murallagraph.service.GraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graphs")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping
    public ResponseEntity<Graph> saveGraph(@RequestBody Graph graph) {
        return ResponseEntity.ok(graphService.saveGraph(graph));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Graph> getGraphByName(@PathVariable String name) {
        return graphService.getGraphByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
