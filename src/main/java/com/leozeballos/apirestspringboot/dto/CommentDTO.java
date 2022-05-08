package com.leozeballos.apirestspringboot.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Long id;

    @NotEmpty(message = "The comment cannot be empty")
    private String comment;

    @NotEmpty(message = "The title cannot be empty")
    @Size(min = 10, message = "The title must be at least 10 characters long")
    private String title;

    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "The email must be valid")
    private String email;

}
