package com.example.privateadsystem.model.dto;

import com.example.privateadsystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long idPost;

    private Long idUser;

    private Long idSubCategory;

    private Long idRegion;

    private String title;
    private String description;
    private double price;
    private String publicationDate;
    private boolean statusSold;
    private boolean statusVip;
}
