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
        Map productData =  productClient.get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map data = (Map) productData.get("data");
        return new ProductDto((String) data.get("id"),
                (String) data.get("name"), (String) data.get("brand"), (String) data.get("categoryId"),
                (Double) data.get("price"), (Map<String, Object>) data.get("features"));
    }

    public List<ProductDto> getProducts() {
        List<Map> productList = (List<Map>) (productClient.get()
                .uri("/all")
                .retrieve()
                .bodyToMono(Map.class)
                .block().get("data"));
        List<ProductDto> list = new ArrayList<>();
        for(Map product : productList) {
            list.add(new ProductDto((String) product.get("id"),
                    (String) product.get("name"), (String) product.get("brand"), (String) product.get("categoryId"),
                    (Double) product.get("price"), (Map<String, Object>) product.get("features")));
        }
        return list;
    }
}
