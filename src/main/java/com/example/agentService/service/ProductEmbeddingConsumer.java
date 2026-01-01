package com.example.agentService.service;

import com.example.agentService.Dto.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEmbeddingConsumer {

    private final EmbeddingPipeline embeddingPipeline;

    @KafkaListener(
            topics = "product.events",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(ProductEvent event) {
        try {
            embeddingPipeline.process(event);
        } catch (Exception e) {
            throw e;
        }

    }
}
