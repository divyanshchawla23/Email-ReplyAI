package com.dc.Email_ReplyAI.controller;

import com.dc.Email_ReplyAI.dto.EmailRequest;
import com.dc.Email_ReplyAI.service.EmailGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public String generateEmailReply(@RequestBody EmailRequest emailRequest) {
        return emailGeneratorService.emailReply(emailRequest);
    }

}