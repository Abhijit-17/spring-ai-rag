package springai.spring_ai_rag.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springai.spring_ai_rag.models.Answer;
import springai.spring_ai_rag.models.Question;
import springai.spring_ai_rag.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class QuestionController {

    private final OpenAIService openAIService;

    //constructor injection
    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/ask/question")
    public Answer postMethodName(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }
    


}
