package com.example.agentService.client;

import com.example.agentService.Dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {

    private final WebClient productClient;

    public ProductDto getProductById(String id) {
        return productClient.get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();

    }

    public List<ProductDto> getProducts() {
        return productClient.get()
                .uri("/all")
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList()
                .block();


    }
}
