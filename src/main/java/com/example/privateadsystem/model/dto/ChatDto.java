package com.example.privateadsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatDto {

    private Long idChat;

    private Long idUserFrom;

    private Long idUserTo;

    private String name;
}
