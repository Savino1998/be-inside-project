package com.reply.insideproject.dto;

import com.reply.insideproject.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewArticleDto {

    private Long id;
    private Category category;
    private String body;
    private String name;

}
