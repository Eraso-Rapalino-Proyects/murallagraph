package com.murallagraph.controller;

import com.murallagraph.entity.StoredGraph;
import com.murallagraph.service.StoredGraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stored-graphs")
public class StoredGraphController {

    private final StoredGraphService storedGraphService;

    public StoredGraphController(StoredGraphService storedGraphService) {
        this.storedGraphService = storedGraphService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoredGraph> getStoredGraphById(@PathVariable String id) {
        return storedGraphService.getStoredGraphById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoredGraph(@PathVariable String id) {
        storedGraphService.deleteStoredGraphById(id);
        return ResponseEntity.noContent().build();
    }
}
