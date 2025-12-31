package com.npcverse.ai.controller;

import com.npcverse.ai.service.GeminiNpcService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/npc")
public class NpcController {

    private final GeminiNpcService npcService;

    public NpcController(GeminiNpcService npcService) {
        this.npcService = npcService;
    }

    @GetMapping("/mission")
    public String getMission() {
        return npcService.getNpcResponse();
    }
}