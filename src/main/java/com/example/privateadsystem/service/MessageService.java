package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Message;
import com.example.privateadsystem.model.dto.MessageDto;

import java.util.List;

public interface MessageService {
    Message saveMessage(MessageDto messageDto);
    List<Message> getMessageByChat(long idChat);
    Message updateMessage(long idMessage, MessageDto messageDto);
    void deleteMessage(long idMessage);
}
