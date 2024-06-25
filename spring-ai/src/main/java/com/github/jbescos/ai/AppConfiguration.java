package com.github.jbescos.ai;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

    @Bean
    public EmbeddingModel embeddingModel(@Value("${spring.ai.openai.api-key}") String apiKey) {
        EmbeddingModel embeddingModel = new OpenAiEmbeddingModel(new OpenAiApi(apiKey));
        return embeddingModel;
    }

    @Bean
    public SpeechModel speechModel(@Value("${spring.ai.openai.api-key}") String apiKey) {
        SpeechModel model = new OpenAiAudioSpeechModel(new OpenAiAudioApi(apiKey));
        return model;
    }

    // https://docs.spring.io/spring-ai/reference/api/imageclient.html
    @Bean
    public ImageModel imageModel(@Value("${spring.ai.openai.api-key}") String apiKey) {
        ImageModel imageModel = new OpenAiImageModel(new OpenAiImageApi(apiKey));
        return imageModel;
    }

    @Bean
    public ChatModel chatModel(@Value("${spring.ai.openai.api-key}") String apiKey) {
        ChatModel chatModel = new OpenAiChatModel(new OpenAiApi(apiKey),
                OpenAiChatOptions.builder().withModel(org.springframework.ai.openai.api.OpenAiApi.ChatModel.GPT_3_5_TURBO).build());
        return chatModel;
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