package com.example.privateadsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat", nullable = false)
    private Long idChat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_to")
    private User userTo;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Message> messages;

    @Column(name = "name")
    private String name;
}
