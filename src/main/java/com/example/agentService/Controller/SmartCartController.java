package com.example.agentService.Controller;

import com.example.agentService.Agent.SmartCartAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/cart")
public class SmartCartController {

    private final SmartCartAgent agent;

    @PostMapping("/advise")
    public String advise(@RequestParam String userId,
                         @RequestBody String message) {

        return agent.advise(userId, message);
    }
}

