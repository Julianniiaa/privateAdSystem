package com.example.privateadsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Long idPost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_sub_category")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "id_region")
    private Region region;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Favorite> favorites;

    private String title;
    private String description;
    private double price;
    private Date publicationDate;
    private boolean statusSold;
    private boolean statusVip;
}
