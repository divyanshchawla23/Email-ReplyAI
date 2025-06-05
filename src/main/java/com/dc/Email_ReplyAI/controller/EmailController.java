package com.dc.Email_ReplyAI.controller;

import com.dc.Email_ReplyAI.service.EmailGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailGeneratorService emailGeneratorService;

}