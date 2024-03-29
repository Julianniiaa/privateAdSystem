package com.example.privateadsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat", nullable = false)
    private Long idChat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_to")
    private User userTo;

    @Column(name = "name")
    private String name;
}
