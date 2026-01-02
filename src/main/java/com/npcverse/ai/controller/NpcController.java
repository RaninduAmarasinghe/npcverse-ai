package com.npcverse.ai.controller;

import com.npcverse.ai.service.GeminiNpcService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/npc")
@CrossOrigin(origins = "*") // 1. Allows your frontend to connect without blocking
public class NpcController {

    private final GeminiNpcService npcService;

    public NpcController(GeminiNpcService npcService) {
        this.npcService = npcService;
    }

    // Chat endpoint
    @PostMapping("/chat")
    // 2. Changed 'ChatRequest' to 'Map' so you don't need an extra file
    public String chat(@RequestBody Map<String, String> payload) {
        try {
            String userMessage = payload.get("message");
            return npcService.getNpcResponse(userMessage);
        } catch (Exception e) {
            return "Error: Thisara is offline. (Check Ollama)";
        }
    }
}