package com.leozeballos.apirestspringboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EntryResponse {

    private List<EntryDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalEntries;
    private int totalPages;
    private boolean hasNext;

}
