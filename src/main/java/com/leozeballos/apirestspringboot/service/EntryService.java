package com.leozeballos.apirestspringboot.service;

import com.leozeballos.apirestspringboot.dto.EntryDTO;
import com.leozeballos.apirestspringboot.dto.EntryResponse;

public interface EntryService {
    EntryDTO newEntry(EntryDTO entryDTO);
    EntryResponse getAllEntries(int pageNumber, int pageSize, String sortBy, String sortDir);
    EntryDTO getEntryById(Long id);
    EntryDTO updateEntry(EntryDTO entryDTO, Long id);
    void deleteEntry(Long id);
}
