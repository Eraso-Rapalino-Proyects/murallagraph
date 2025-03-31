package com.murallagraph.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "graphs")
public class StoredGraph {  
    @Id
    private String id;
    private String name;
    private String filePath; 

    public StoredGraph() {}

    public StoredGraph(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}
