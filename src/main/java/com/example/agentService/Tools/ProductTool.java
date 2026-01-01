package com.example.agentService.Tools;

import com.example.agentService.Dto.ProductDto;
import com.example.agentService.client.ProductServiceClient;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductTool {
    private final ProductServiceClient productServiceClient;

    @Tool("Get product info by Id")
    public ProductDto getProduct(String productId) {
        try {
            return productServiceClient.getProductById(productId);
        }catch (Exception e){
            return null;
        }
    }

    @Tool("Get info for all products for finding similarity")
    public List<ProductDto> getProducts() {
        try {
            return productServiceClient.getProducts();
        } catch (Exception e) {
            return null;
        }
    }
}
