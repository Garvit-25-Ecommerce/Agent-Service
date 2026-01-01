package com.example.agentService.client;

import com.example.agentService.Dto.AddToCartRequest;
import com.example.agentService.Dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CartServiceClient {

    private final WebClient cartClient;

    public List<CartItemDto> getCart(String userId) {
        Map cart = cartClient.get()
                .uri("/{userId}", userId)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<CartItemDto> cartItems = new ArrayList<>();
        Map data = (Map) cart.get("data");
        Map<String, LinkedHashMap<String,Object>> products =(Map) data.get("products");
        for(Map.Entry<String, LinkedHashMap<String,Object>> product : products.entrySet()) {
            cartItems.add(new CartItemDto(product.getKey(), (Integer) product.getValue().get("first")));
        }
        return cartItems;
    }

    public String addToCart(String userId, String productId, int qty) {
        return cartClient.post()
                .uri("/add")
                .bodyValue(new AddToCartRequest(userId, productId, qty))
                .retrieve().bodyToMono(String.class)
                .block();
    }
}
