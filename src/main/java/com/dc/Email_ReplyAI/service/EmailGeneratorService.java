package com.dc.Email_ReplyAI.service;

import com.dc.Email_ReplyAI.dto.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailGeneratorService {

    private final ExternalGeminiService externalGeminiService;

    public String emailReply(EmailRequest emailRequest) {

        String prompt = generatePrompt(emailRequest);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        return externalGeminiService.generateResponse(requestBody);

    }

    private String generatePrompt(EmailRequest emailRequest) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("Generate a reply to the following email. " +
                "Please do not include a subject.");

        if (emailRequest.getTone() != null || !emailRequest.getTone().isEmpty()) {
            prompt.append(" The tone should be ").append(emailRequest.getTone()).append(".");
        }

        prompt.append("\nOrignal Email:\n")
                .append(emailRequest.getContent());

        return prompt.toString();
    }

}