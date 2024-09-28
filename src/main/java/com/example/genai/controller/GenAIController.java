
package com.example.genai.controller;

import com.example.genai.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/genai")
public class GenAIController {

    private final AIService aiService;

    public GenAIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public Mono<ResponseEntity<String>> generateAIResponse(@RequestBody Map<String, String> input) {
        String userInput = input.get("input");

        return aiService.generateResponse(userInput)
                .map(response -> ResponseEntity.ok(response))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error generating response: " + e.getMessage())));
    }
}
        