package com.example.privateadsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rating")
@NoArgsConstructor
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rating", nullable = false)
    private Long idRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_to")
    private User userTo;

    @Column(name = "value")
    private int value;

    @Column(name = "date_rating")
    private Date dateRating;
}
