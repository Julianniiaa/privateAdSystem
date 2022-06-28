package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Message;
import com.example.privateadsystem.repository.ChatRepository;
import com.example.privateadsystem.repository.MessageRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.MessageService;
import com.example.privateadsystem.web.dto.MessageDto;

import java.time.LocalDateTime;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    public MessageServiceImpl(MessageRepository messageRepository,
                              ChatRepository chatRepository,
                              UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Message saveMessage(MessageDto messageDto) {
        Message message = Message.builder()
                .chat(chatRepository.findChatByIdChat(messageDto.getIdChat()))
                .user(userRepository.findByIdUser(messageDto.getIdUser()))
                .publicationTime(LocalDateTime.now())
                .text(messageDto.getText())
                .status(false)
                .build();
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessageByChat(long idChat) {
        return messageRepository.findMessagesByChat_IdChat(idChat);
    }

    @Override
    public Message updateMessage(long idMessage, MessageDto messageDto) {
        Message message = messageRepository.findMessageByIdMessage(idMessage);
        message.setText(messageDto.getText());
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(long idMessage) {
        messageRepository.deleteById(idMessage);
    }
}
