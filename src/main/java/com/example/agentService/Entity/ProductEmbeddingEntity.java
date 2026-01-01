package com.example.agentService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("product_embeddings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEmbeddingEntity {
    @Id
    private String id;

    private String productId;
    private String model;
    private Integer version;

    private float[] embeddingVector;

    private Instant createdAt;
    private Instant updatedAt;
}
