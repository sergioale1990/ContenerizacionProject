package org.gestion.products.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LogSenderService {

    private final RestTemplate restTemplate;

    @Value("${logs.service.url}")
    private String logsServiceUrl;

    public void sendLog(String user, String module, String description, String message, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);

            Map<String, Object> body = new HashMap<>();
            body.put("user", user);
            body.put("module", module);
            body.put("description", description);
            body.put("message", message);
            body.put("level", "INFO");

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(logsServiceUrl, request, Void.class);
            System.out.println("Log enviado a logs-service");

        } catch (Exception e) {
            System.out.println("Error enviando log: " + e.getMessage());
        }
    }
}
