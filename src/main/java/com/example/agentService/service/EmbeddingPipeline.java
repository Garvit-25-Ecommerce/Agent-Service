package com.example.agentService.service;

import com.example.agentService.Dto.ProductEvent;
import com.example.agentService.Entity.ProductEmbeddingEntity;
import com.example.agentService.repository.ProductEmbeddingRepository;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EmbeddingPipeline {

    private final EmbeddingModel embeddingModel;
    private final ProductEmbeddingRepository repository;

    public void process(ProductEvent event) {
        String text = buildText(event);

        float[] vector = embeddingModel
                .embed(text)
                .content()
                .vector();

        ProductEmbeddingEntity embedding = new ProductEmbeddingEntity(
                null,
                event.getProductId(),
                "mxbai-embed-large",
                1,
                vector,
                Instant.now(),
                Instant.now()
        );

        repository.save(embedding);
    }

    private String buildText (ProductEvent event) {
        return String.join(" ",
                event.getName(),
                event.getBrand(),
                event.getCategory(),
                String.join("", event.getFeatures().toString()));
    }
}
