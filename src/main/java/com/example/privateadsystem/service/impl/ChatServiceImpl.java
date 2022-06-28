package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Chat;
import com.example.privateadsystem.model.User;
import com.example.privateadsystem.repository.ChatRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.ChatService;
import com.example.privateadsystem.web.dto.ChatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    public ChatServiceImpl (ChatRepository chatRepository,
                            UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Chat saveChat(ChatDto chatDto) {
        Chat chatFound = chatRepository.findChatByUserFrom_IdUserAndUserTo_IdUser(
                chatDto.getIdUserFrom(), chatDto.getIdUserTo());
        if (chatFound == null) {
            User userFrom = userRepository.findByIdUser(chatDto.getIdUserFrom());
            User userTo = userRepository.findByIdUser(chatDto.getIdUserTo());
            Chat chat = Chat.builder()
                    .userFrom(userFrom)
                    .userTo(userTo)
                    .name(userFrom.getUsername() + "_" + userTo.getUsername())
                    .build();
            return chatRepository.save(chat);
        }
        return chatFound;
    }

    @Override
    public List<Chat> getChatsByIdUserFromAndIdUserTo(long idUser) {
        List<Chat> chats = chatRepository.findChatsByUserFrom_IdUserOrUserTo_IdUser(idUser, idUser);
//        chats.addAll(chatRepository.findChatsByUserTo_IdUser(idUser));
        return chats;
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }
}
