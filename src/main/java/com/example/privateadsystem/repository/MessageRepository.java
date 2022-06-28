package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByChat_IdChat(long idChat);
    Message findMessageByIdMessage(long idMessage);
}
