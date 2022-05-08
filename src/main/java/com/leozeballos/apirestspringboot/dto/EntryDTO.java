package com.leozeballos.apirestspringboot.dto;

import com.leozeballos.apirestspringboot.entity.Comment;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryDTO {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments;

}
