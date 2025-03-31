package com.murallagraph.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class GeoJsonSender {

    private final String SERVER_URL = "http://localhost:3000/upload-geojson";

    public String sendGeoJson(String geoJsonString) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<String> requestEntity = new HttpEntity<>(geoJsonString, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(SERVER_URL, HttpMethod.POST, requestEntity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return "Respuesta del servidor receptor: " + response.getBody();
            } else {
                return "Error al enviar el archivo GeoJSON.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Hubo un error al conectar con el servidor.";
        }
    }
}
