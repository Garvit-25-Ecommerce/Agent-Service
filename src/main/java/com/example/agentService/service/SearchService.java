package com.example.agentService.service;

import com.example.agentService.Dto.ProductDto;
import com.example.agentService.Entity.ProductEmbeddingEntity;
import com.example.agentService.client.ProductServiceClient;
import com.example.agentService.repository.ProductEmbeddingRepository;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProductEmbeddingRepository repository;
    private final EmbeddingModel embeddingModel;
    private final ProductServiceClient productServiceClient;

    private List<String> semanticSearch(String query, int limit) {
        float[] queryVector = embeddingModel.embed(query).content().vector();

        List<ProductEmbeddingEntity> embeddings = repository.findAll();

        return embeddings.stream()
                .map(e -> Map.entry(
                        e.getId(), cosineSimilarity(queryVector, e.getEmbeddingVector())
                ))
                .limit(limit)
                .map(Map.Entry::getKey)
                .toList();
    }

    private double cosineSimilarity(float[] a, float[] b) {
        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;
        for(int i=0;i<a.length; i++) {
            dotProduct += a[i] * b[i];
            magnitudeA += a[i] * a[i];
            magnitudeB += b[i] * b[i];
        }

        return dotProduct / (Math.sqrt(magnitudeA) * Math.sqrt(magnitudeB));
    }

    public List<ProductDto> search(String query) {
        List<String> productIds = semanticSearch(query, 20);
        return productIds.stream()
                .map(productServiceClient::getProductById)
                .toList();
    }
}
