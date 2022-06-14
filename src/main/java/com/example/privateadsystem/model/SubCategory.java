package com.example.privateadsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sub_category")
@NoArgsConstructor
@Data
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub_category", nullable = false)
    private Long idSubCategory;

    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

}
