package com.npcverse.ai.service;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class GeminiNpcService {

    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getNpcResponse() {

        String mission =
                "The player must travel to the northern forest, defeat the bandits, " +
                        "and rescue the captured villagers.";

        String prompt =
                "You are a medieval fantasy NPC. Explain the mission using different wording, " +
                        "but keep the meaning the same. Do not add new objectives.\n\n" +
                        "Mission:\n" + mission;

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        String url = GEMINI_URL + "?key=" + System.getenv("GEMINI_API_KEY");

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, entity, Map.class);

        Map candidate = (Map) ((List<?>) response.getBody().get("candidates")).get(0);
        Map content = (Map) candidate.get("content");
        Map part = (Map) ((List<?>) content.get("parts")).get(0);

        return part.get("text").toString();
    }
}