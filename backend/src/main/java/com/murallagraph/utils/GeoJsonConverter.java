package com.murallagraph.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murallagraph.entity.Graph;
import com.murallagraph.entity.Node;
import com.murallagraph.entity.Edge;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class GeoJsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toGeoJson(Graph graph) {
        try {
            StringBuilder geoJson = new StringBuilder();
            geoJson.append("{ \"type\": \"FeatureCollection\", \"features\": [");


            for (Node node : graph.getNodes()) {
                geoJson.append("{\"type\": \"Feature\", \"properties\": {")
                        .append("\"id\": \"").append(node.getId()).append("\",")
                        .append("\"type\": \"").append(node.getType()).append("\",")
                        .append("\"initialContent\": \"").append(node.getInitialContent()).append("\",")
                        .append("\"maximumCapacity\": ").append(node.getMaximumCapacity()).append(",")
                        .append("\"enable\": ").append(node.isEnable())
                        .append("}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [")
                        .append(node.getLng()).append(", ").append(node.getLat()).append("] } },");
            }


            for (Edge edge : graph.getEdges()) {
                geoJson.append("{\"type\": \"Feature\", \"properties\": {")
                        .append("\"startNodeId\": \"").append(edge.getStartNodeId()).append("\",")
                        .append("\"endNodeId\": \"").append(edge.getEndNodeId()).append("\",")
                        .append("\"weight\": ").append(edge.getWeight()).append(",")
                        .append("\"time\": ").append(edge.getTime()).append(",")
                        .append("\"capacity\": ").append(edge.getCapacity()).append(",")
                        .append("\"distance\": ").append(edge.getDistance()).append(",")
                        .append("\"enable\": ").append(edge.isEnable())
                        .append("}, \"geometry\": { \"type\": \"LineString\", \"coordinates\": [");

                for (double[] coord : edge.getArrowPolygon()) {
                    geoJson.append("[").append(coord[1]).append(", ").append(coord[0]).append("],");
                }

                geoJson.setLength(geoJson.length() - 1); 
                geoJson.append("] } },");
            }

            geoJson.setLength(geoJson.length() - 1); 
            geoJson.append("] }");

            return geoJson.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static Graph fromGeoJson(String geoJson) {
        try {
            JsonNode root = objectMapper.readTree(geoJson);
            Graph graph = new Graph();
            List<Node> nodes = new ArrayList<Node>();
            List<Edge> edges = new ArrayList<Edge>();

            for (JsonNode feature : root.get("features")) {
                String type = feature.get("geometry").get("type").asText();
                JsonNode properties = feature.get("properties");

                if ("Point".equals(type)) {
                    Node node = new Node();
                    node.setId(properties.get("id").asText());
                    node.setType(properties.get("type").asText());
                    node.setInitialContent(properties.get("initialContent").asText());
                    node.setMaximumCapacity(properties.get("maximumCapacity").asInt());
                    node.setEnable(properties.get("enable").asBoolean());

                    JsonNode coordinates = feature.get("geometry").get("coordinates");
                    node.setLat(coordinates.get(1).asDouble());
                    node.setLng(coordinates.get(0).asDouble());

                    nodes.add(node);
                } else if ("LineString".equals(type)) {
                    Edge edge = new Edge();
                    edge.setStartNodeId(properties.get("startNodeId").asText());
                    edge.setEndNodeId(properties.get("endNodeId").asText());
                    edge.setWeight(properties.get("weight").asDouble());
                    edge.setTime(properties.get("time").asDouble());
                    edge.setCapacity(properties.get("capacity").asDouble());
                    edge.setDistance(properties.get("distance").asDouble());
                    edge.setEnable(properties.get("enable").asBoolean());

                    JsonNode coordinates = feature.get("geometry").get("coordinates");
                    double[][] arrowPolygon = new double[coordinates.size()][2];
                    for (int i = 0; i < coordinates.size(); i++) {
                        arrowPolygon[i][0] = coordinates.get(i).get(1).asDouble();
                        arrowPolygon[i][1] = coordinates.get(i).get(0).asDouble();
                    }
                    edge.setArrowPolygon(arrowPolygon);

                    edges.add(edge);
                }
            }

            graph.setNodes(nodes);
            graph.setEdges(edges);
            return graph;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
