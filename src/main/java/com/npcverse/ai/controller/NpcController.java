package com.npcverse.ai.controller;

import com.npcverse.ai.dto.ChatRequest;
import com.npcverse.ai.service.GeminiNpcService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/npc")
public class NpcController {

    private final GeminiNpcService npcService;

    public NpcController(GeminiNpcService npcService) {
        this.npcService = npcService;
    }

    // Initial mission page load
    @GetMapping("/mission")
    public String getMission() {
        return npcService.getNpcResponse("Tell me about work around here.");
    }

    // Chat endpoint
    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return npcService.getNpcResponse(request.getMessage());
    }

}
