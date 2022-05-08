package com.leozeballos.apirestspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryDTO {

    private Long id;
    private String title;
    private String description;
    private String content;

}
