package com.example.privateadsystem.controller;

import com.example.privateadsystem.exception.NotEntityException;
import com.example.privateadsystem.model.Chat;
import com.example.privateadsystem.model.Message;
import com.example.privateadsystem.service.ChatService;
import com.example.privateadsystem.service.MessageService;
import com.example.privateadsystem.service.UserService;
import com.example.privateadsystem.model.dto.ChatDto;
import com.example.privateadsystem.model.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("users/{id}/chats")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;

    public ChatController(ChatService chatService,
                          MessageService messageService,
                          UserService userService) {
        this.chatService = chatService;
        this.messageService = messageService;
        this.userService = userService;
    }

    public boolean ifCurrentUsername(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authorized user: {}", auth.getName());
        return Objects.equals(auth.getName(), userService.getUsername(id));
    }

    @PostMapping
    public Chat createChat(@RequestBody ChatDto chatDto,
                           @PathVariable(name = "id") long id) {
        logger.info("Create chat between {}, {}", id, chatDto.getIdUserTo());
        if (ifCurrentUsername(id)) {
            chatDto.setIdUserFrom(id);
            return chatService.saveChat(chatDto);
        }
        logger.info("No user login");
        throw new NotEntityException("No user login");
    }

    @GetMapping
    public List<Chat> getAllChatsByUser(@PathVariable(name = "id") long id) {
        logger.info("Get all chats by user: {}", id);
        return chatService.getChatsByIdUserFromAndIdUserTo(id);
    }

    @PostMapping("/{idChat}")
    public Message createMessage(@PathVariable(name = "id") long id,
                                 @PathVariable(name = "idChat") long idChat,
                                 @RequestBody MessageDto messageDto) {
        logger.info("Create message between {}, {}", id, idChat);
        messageDto.setIdUser(id);
        messageDto.setIdChat(idChat);
        return messageService.saveMessage(messageDto);
    }

    @GetMapping("/{idChat}")
    public List<Message> getMessageByChat(@PathVariable(name = "id") long id,
                                          @PathVariable(name = "idChat") long idChat) {
        logger.info("Get messages between {}, {}", id, idChat);
        return messageService.getMessageByChat(idChat);
    }

    @PostMapping("/{idChat}/{idMessage}")
    public Message updateMessage(@PathVariable(name = "id") long id,
                                 @PathVariable(name = "idChat") long idChat,
                                 @PathVariable(name = "idMessage") long idMessage,
                                 @RequestBody MessageDto messageDto) {
        logger.info("Update message between {}, {}", id, idChat);
        return messageService.updateMessage(idMessage, messageDto);
    }

    @DeleteMapping("/{idChat}/{idMessage}")
    public void deleteMessage(@PathVariable(name = "id") long id,
                              @PathVariable(name = "idChat") long idChat,
                              @PathVariable(name = "idMessage") long idMessage) {
        logger.info("Delete message between {}, {}", id, idChat);
        messageService.deleteMessage(idMessage);
    }
}
