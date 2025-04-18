package com.murallagraph.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.murallagraph.utils.GeoJsonConverter;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "graphs")
public class Graph {

    @Id
    private String id;
    private String name;
    private List<Node> nodes;
    private List<Edge> edges; 
    private String geojsonString;

    public Graph() {
        this.nodes = new ArrayList<Node>();
        this.edges = new ArrayList<Edge>();
        this.updateGeoJson();
    }

    public Graph(String name) {
        this();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
        updateGeoJson();  
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
        updateGeoJson();  
    }

    public String getGeoJsonString(){
        return geojsonString;
    }

    public void updateGeoJson() {
        this.geojsonString = GeoJsonConverter.toGeoJson(this);
    }
}
