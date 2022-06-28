package com.example.privateadsystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDto {

    private Long idComment;

    private String text;

    private  Long idPost;

    private Long idUser;
}
