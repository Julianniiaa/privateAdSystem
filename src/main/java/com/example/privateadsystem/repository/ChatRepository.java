package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findChatByUserFrom_IdUserAndUserTo_IdUser(long idUserFrom, long idUserTo);
    List<Chat> findChatsByUserFrom_IdUserOrUserTo_IdUser(long idUserFrom, long idUserTo);
    List<Chat> findChatsByUserTo_IdUser(long idUser);
    Chat findChatByIdChat(long idChat);
}
