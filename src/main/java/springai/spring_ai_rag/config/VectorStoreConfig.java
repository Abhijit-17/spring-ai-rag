package springai.spring_ai_rag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;

@Configuration
public class VectorStoreConfig {

    @Bean
    public SimpleVectorStore vectorStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) {
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());

        // to do - add data
        return vectorStore;
    }
}
