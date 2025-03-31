package com.murallagraph.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nodes")
public class Node {

    @Id
    private String id;
    private double lat;
    private double lng;
    private double latlng[];
    private String type;
    private String initialContent;
    private int maximumCapacity;
    private boolean enable;

    public Node() {
    }

    public Node(String id, double lat, double lng, String type, String initialContent, int maximumCapacity,
            boolean enable) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.latlng = new double[] { lat, lng };
        this.type = type;
        this.initialContent = initialContent;
        this.maximumCapacity = maximumCapacity;
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitialContent() {
        return initialContent;
    }

    public void setInitialContent(String initialContent) {
        this.initialContent = initialContent;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public double[] getLatLng() {
        return latlng;
    };
}
