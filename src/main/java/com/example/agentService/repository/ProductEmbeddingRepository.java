package com.example.agentService.repository;

import com.example.agentService.Entity.ProductEmbeddingEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductEmbeddingRepository extends MongoRepository<ProductEmbeddingEntity, String > {

    Optional<ProductEmbeddingEntity> findByProductId(String productId);

    boolean existsByProductId(String productId);

    void deleteByProductId(String productId);

    @NotNull
    List<ProductEmbeddingEntity> findAll();
}
