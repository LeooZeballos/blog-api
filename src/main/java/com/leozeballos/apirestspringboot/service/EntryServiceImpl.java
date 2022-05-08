package com.leozeballos.apirestspringboot.service;

import com.leozeballos.apirestspringboot.dto.EntryDTO;
import com.leozeballos.apirestspringboot.entity.Entry;
import com.leozeballos.apirestspringboot.exception.ResourceNotFoundException;
import com.leozeballos.apirestspringboot.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    private EntryDTO mapEntryToDTO(Entry entry) {
        EntryDTO responseEntry = new EntryDTO();
        responseEntry.setId(entry.getId());
        responseEntry.setTitle(entry.getTitle());
        responseEntry.setDescription(entry.getDescription());
        responseEntry.setContent(entry.getContent());
        return responseEntry;
    }

    private Entry mapDTOtoEntry(EntryDTO entryDTO) {
        Entry entry = new Entry();
        entry.setId(entryDTO.getId());
        entry.setTitle(entryDTO.getTitle());
        entry.setDescription(entryDTO.getDescription());
        entry.setContent(entryDTO.getContent());
        return entry;
    }

    @Override
    public EntryDTO newEntry(EntryDTO entryDTO) {
        Entry entry = mapDTOtoEntry(entryDTO);
        entry = entryRepository.save(entry);
        return mapEntryToDTO(entry);
    }

    @Override
    public List<EntryDTO> getAllEntries() {
        List<Entry> responseEntries = entryRepository.findAll();
        return responseEntries.stream().map(this::mapEntryToDTO).collect(Collectors.toList());
    }

    @Override
    public EntryDTO getEntryById(Long id) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "id", id));
        return mapEntryToDTO(entry);
    }

    @Override
    public EntryDTO updateEntry(EntryDTO entryDTO, Long id) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "id", id));
        entry.setTitle(entryDTO.getTitle());
        entry.setDescription(entryDTO.getDescription());
        entry.setContent(entryDTO.getContent());
        Entry updatedEntry = entryRepository.save(entry);
        return mapEntryToDTO(updatedEntry);
    }

    @Override
    public void deleteEntry(Long id) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "id", id));
        entryRepository.delete(entry);
    }

}
