package com.example.general;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.Ignore;
import org.junit.Test;

public class LangChainTest {

    private static final String API_KEY = "put yours";
    
    @Test
    @Ignore
    public void test() {
        OpenAiChatModel model = OpenAiChatModel.withApiKey(API_KEY);
        String answer = model.generate("Say 'Hello World'");
        System.out.println(answer);
    }
}
