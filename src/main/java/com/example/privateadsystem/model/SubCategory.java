package com.example.privateadsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sub_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub_category", nullable = false)
    private Long idSubCategory;

    @Column(name = "sub_category_name")
    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
