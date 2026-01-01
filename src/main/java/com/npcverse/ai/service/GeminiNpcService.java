package com.npcverse.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
@Service
public class GeminiNpcService { // Keeping name for compatibility

    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String modelName;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String missionContext =
            "MISSION: Silas the Rat stole a silver locket and is hiding at the Abandoned Mill. Reward: 20 gold.";

    public String getNpcResponse(String userMessage) {
        String systemInstruction = "You are Barnaby, a medieval tavern owner. " +
                "Respond to the player using this MISSION DATA: " + missionContext +
                ". Stay in character. User: " + userMessage;

        // Ollama JSON format
        Map<String, Object> body = Map.of(
                "model", modelName,
                "prompt", systemInstruction,
                "stream", false // Get a single string instead of a stream
        );

        return callOllama(body);
    }

    private String callOllama(Map<String, Object> body) {
        try {
            // Send request to localhost:11434
            ResponseEntity<Map> response = restTemplate.postForEntity(ollamaUrl, body, Map.class);

            // Ollama returns text in a field called "response"
            return response.getBody().get("response").toString();
        } catch (Exception e) {
            System.err.println("Ollama Error: " + e.getMessage());
            return "Barnaby grunts: 'My head hurts... come back later.'";
        }
    }
}

/*
@Service
public class GeminiNpcService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ CORRECT MODEL & API VERSION (confirmed by curl)
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

    // ===================== MAIN NPC RESPONSE =====================
    public String getNpcResponse(String userMessage) {

        String prompt =
                "You are Barnaby, a medieval tavern owner.\n" +
                        "Stay in character. Use medieval slang.\n\n" +
                        "Player says: " + userMessage;

        // ✅ THIS is where your JSON goes
        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        return callGemini(body);
    }

    // ===================== CALL GEMINI =====================
    private String callGemini(Map<String, Object> body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(body, headers);

            String url = GEMINI_URL + "?key=" + apiKey;

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, entity, Map.class);

            return extractText(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return "Barnaby mutters: 'The spirits be silent today...'";
        }
    }

    // ===================== PARSE RESPONSE =====================
    private String extractText(Map<String, Object> body) {
        try {
            List<?> candidates = (List<?>) body.get("candidates");
            Map<?, ?> content = (Map<?, ?>) candidates.get(0);
            Map<?, ?> message = (Map<?, ?>) content.get("content");
            List<?> parts = (List<?>) message.get("parts");
            return parts.get(0).toString().replace("{text=", "").replace("}", "");
        } catch (Exception e) {
            return "Barnaby scratches his beard, saying naught.";
        }
    }
} */
