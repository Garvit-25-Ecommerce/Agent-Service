package com.example.agentService.Tools;

import com.example.agentService.Dto.CartItemDto;
import com.example.agentService.client.CartServiceClient;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartTool {

    private final CartServiceClient client;

    @Tool("Get cart Info for a user")
    public List<CartItemDto> getCart(String userId) {
        try {
            return client.getCart(userId);
        } catch (Exception e) {
             return null;
        }
    }

    @Tool("Add product to user's cart")
    public String addToCart(String userID, String productId, int qty) {
        return client.addToCart(userID, productId, qty);
    }
}
