package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Chat;
import com.example.privateadsystem.model.dto.ChatDto;

import java.util.List;

public interface ChatService {
    Chat saveChat(ChatDto chatDto);
    List<Chat> getChatsByIdUserFromAndIdUserTo(long idUser);
    List<Chat> getAllChats();
}
