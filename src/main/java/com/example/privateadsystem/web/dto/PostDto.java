package com.example.privateadsystem.web.dto;

import com.example.privateadsystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class PostDto {

    private Long idPost;

    private Long idUser;

    private Long idSubCategory;

    private Long idRegion;

    private String title;
    private String description;
    private double price;
    @DateTimeFormat(pattern = "dd.mm.yyyy HH:mm")
    private DateFormatSymbols publicationDate;
    private boolean statusSold;
    private boolean statusVip;

}
