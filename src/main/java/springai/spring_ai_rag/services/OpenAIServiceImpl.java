package springai.spring_ai_rag.services;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springai.spring_ai_rag.models.Answer;
import springai.spring_ai_rag.models.Question;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;
    private final SimpleVectorStore vectorStore;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Override
    public Answer getAnswer(Question question) {

        // Perform a similarity search in the vector store
        // to find relevant documents based on the question.
        // The topK parameter specifies how many documents to retrieve.
        // In this case, we are retrieving the top 4 documents.
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                .query(question.question())
                .topK(10)
                .build());
        
        // Extract the text content from the retrieved documents.
        // This is necessary to prepare the content for the prompt.
        // The getText() method is used to get the text content of each document.
        // The result is a list of strings containing the text of each document.
        List<String> contentList = documents.stream().map(doc -> doc.getText()).toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of(
                "input", question.question(),
                "documents", String.join("\n", contentList)
        ));

        // printing the contentList for debugging purposes
        contentList.forEach(System.out::println);

        // Call the chat model with the prepared prompt.
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

}
