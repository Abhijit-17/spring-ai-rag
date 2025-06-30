package springai.spring_ai_rag.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "asb.aiapp")
public class VectorStoreProperties {

    private String vectorStorePath;

    public String getVectorStorePath() {
        return vectorStorePath;
    }

    public void setVectorStorePath(String vectorStorePath) {
        this.vectorStorePath = vectorStorePath;
    }

}
