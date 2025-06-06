package com.dc.Email_ReplyAI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
public class ExternalGeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String GeminiApiUrl;
    @Value("${gemini.key}")
    private String GeminiApiKey;

    public ExternalGeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    String generateResponse(Map<String, Object> requestBody) {
        String fullUrl = GeminiApiUrl + "?key=" + GeminiApiKey;
        log.info("Sending request to Gemini API: {}", fullUrl);
        String response = webClient.post()
                .uri(fullUrl)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractResponse(response);
    }

    private String extractResponse(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(response);
            return node.path("candidates").get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            return "Error fetching response: " + e.getMessage();
        }
    }

}