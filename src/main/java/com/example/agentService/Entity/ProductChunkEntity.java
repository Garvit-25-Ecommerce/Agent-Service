package com.example.agentService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product_chunks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductChunkEntity {
    @Id
    private String id;

    private String productId;
    private Integer chunkIndex;
    private float[] vector;
    private String text;
}
