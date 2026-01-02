package com.npcverse.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeminiNpcService {

    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String modelName;

    private final RestTemplate restTemplate = new RestTemplate();

    // 1. UPDATED MISSION: Modern heist context
    private final String missionContext =
            "MISSION: We need to steal a Red Sports Car parked at the Colombo City Hotel. " +
                    "The keys are with the valet. Payoff: 50,000 LKR.";

    public String getNpcResponse(String userMessage) {
        // 2. UPDATED PERSONA: Thisara (Modern Fixer/Street Smart)
        String systemInstruction = "You are Thisara, a street-smart fixer and car thief. " +
                "Context: " + missionContext + ". " +
                "Reply to the user briefly in character. Be cool and casual. " +
                "Do not include 'Thisara:' or 'System:' in your reply. " +
                "User says: " + userMessage;

        // 3. Build Request
        Map<String, Object> body = Map.of(
                "model", modelName,
                "prompt", systemInstruction,
                "stream", false
        );

        return callOllama(body);
    }

    private String callOllama(Map<String, Object> body) {
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ollamaUrl, body, Map.class);

            if (response.getBody() != null && response.getBody().containsKey("response")) {
                return response.getBody().get("response").toString().trim();
            }
            return "Thisara looks away, ignoring you.";

        } catch (Exception e) {
            System.err.println("Ollama Error: " + e.getMessage());
            return "Thisara is busy on a call.";
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
