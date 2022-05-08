package com.leozeballos.apirestspringboot.service;

import com.leozeballos.apirestspringboot.dto.EntryDTO;

import java.util.List;

public interface EntryService {
    public EntryDTO newEntry(EntryDTO entryDTO);
    public List<EntryDTO> getAllEntries();
    public EntryDTO getEntryById(Long id);
    public EntryDTO updateEntry(EntryDTO entryDTO, Long id);
    public void deleteEntry(Long id);
}
