package com.murallagraph.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.murallagraph.utils.MathUtils;

@Document(collection = "edges")
public class Edge {

    @Id
    private String id;

    private String startNodeId;
    private String endNodeId;
    private Node startNode;
    private Node endNode;
    private double weight;
    private double capacity;
    private double time;
    private double distance;
    private double arrowPolygon[][];
    private boolean enable;

    public Edge() {
    }

    public Edge(String id, Node startNode, Node endNode, double weight, double capacity, double time) {
        this.id = id;
        this.startNode = startNode;
        this.endNode = endNode;
        this.startNodeId = startNode.getId();
        this.endNodeId = endNode.getId();
        this.weight = weight;
        this.capacity = capacity;
        this.time = time;
        this.distance = MathUtils.calculateDistance(startNode.getLat(), startNode.getLng(), endNode.getLat(),
                endNode.getLng());
        this.arrowPolygon = MathUtils.calculateEdgeDistance(startNode.getLatLng(), endNode.getLatLng(), 0.00009);
        this.enable = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(String startNodeId) {
        this.startNodeId = startNodeId;
    }

    public String getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public double[][] getArrowPolygon() {
        return arrowPolygon;
    }

    public void setArrowPolygon(double[][] arrowPolygon) {
        this.arrowPolygon = arrowPolygon;
    }
}