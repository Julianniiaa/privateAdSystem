package com.example.privateadsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageDto {

    private Long idMessage;

    private Long idChat;

    private Long idUser;

    private String text;
}
