package springai.spring_ai_rag.services;

import springai.spring_ai_rag.models.Answer;
import springai.spring_ai_rag.models.Question;

public interface OpenAIService {
    Answer getAnswer(Question question);
}
