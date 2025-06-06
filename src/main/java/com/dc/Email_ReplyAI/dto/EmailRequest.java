package com.dc.Email_ReplyAI.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmailRequest {
    private String content;
    private String tone;
}