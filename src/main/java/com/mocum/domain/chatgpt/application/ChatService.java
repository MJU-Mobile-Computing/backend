package com.mocum.domain.chatgpt.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocum.domain.chatgpt.dto.ChatRequest;
import com.mocum.domain.chatgpt.dto.ChatRequest.Message;
import com.mocum.global.payload.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseCustom<String> getChatResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("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");

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("gpt-3.5-turbo");
        chatRequest.setMessages(Arrays.asList(
                new Message("user", prompt)
        ));

        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(chatRequest, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/chat/completions", requestEntity, String.class);
            String responseBody = response.getBody();

            // JSON 응답 파싱
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

            // 불필요한 문자열 제거 및 필요한 정보 추출
            String cleanedContent = extractNutritionalInfo(content);

            return ResponseCustom.OK(cleanedContent);
        } catch (HttpClientErrorException e) {
            System.out.println("HTTP Status Code: " + e.getStatusCode());
            System.out.println("HTTP Response Body: " + e.getResponseBodyAsString());
            return ResponseCustom.OK(e.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseCustom.OK("An error occurred while processing the response.");
        }
    }

    private String extractNutritionalInfo(String content) {
        // 필요한 정보만 추출
        StringBuilder result = new StringBuilder();
        String[] lines = content.split("\n");

        for (String line : lines) {
            if (line.contains("칼로리") || line.contains("탄수화물") || line.contains("단백질") || line.contains("지방") || line.contains("나트륨") || line.contains("철분") || line.contains("비타민")) {
                result.append(line.trim()).append(" ");
            }
        }

        // \n 제거
        String finalContent = result.toString().replace("\\n", "").trim();

        return finalContent;
    }
}
