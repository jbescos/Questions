package com.github.jbescos.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiApi.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

//    @Bean
//    public SpeechClient speechClient(@Value("${spring.ai.openai.api-key}") String apiKey) {
//        return new OpenAiAudioSpeechClient(new OpenAiAudioApi(apiKey));
//    }
//
//    @Bean
//    public ImageClient imageClient(@Value("${spring.ai.openai.api-key}") String apiKey) {
//        return new OpenAiImageClient(new OpenAiImageApi(apiKey));
//    }

    @Bean
    public ChatClient chatClient(@Value("${spring.ai.openai.api-key}") String apiKey) {
        return ChatClient.builder(new OpenAiChatModel(new OpenAiApi(apiKey),
                OpenAiChatOptions.builder().withModel(ChatModel.GPT_3_5_TURBO).build())).build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}