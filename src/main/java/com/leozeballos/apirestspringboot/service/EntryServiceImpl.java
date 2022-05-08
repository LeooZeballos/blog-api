package com.leozeballos.apirestspringboot.service;

import com.leozeballos.apirestspringboot.dto.EntryDTO;
import com.leozeballos.apirestspringboot.dto.EntryResponse;
import com.leozeballos.apirestspringboot.entity.Entry;
import com.leozeballos.apirestspringboot.exception.ResourceNotFoundException;
import com.leozeballos.apirestspringboot.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return EntryDTO.builder()
                .id(entry.getId())
                .title(entry.getTitle())
                .description(entry.getDescription())
                .content(entry.getContent())
                .build();
    }

    private Entry mapDTOtoEntry(EntryDTO entryDTO) {
        return Entry.builder()
                .id(entryDTO.getId())
                .title(entryDTO.getTitle())
                .description(entryDTO.getDescription())
                .content(entryDTO.getContent())
                .build();
    }

    @Override
    public EntryDTO newEntry(EntryDTO entryDTO) {
        Entry entry = mapDTOtoEntry(entryDTO);
        entry = entryRepository.save(entry);
        return mapEntryToDTO(entry);
    }

    @Override
    public EntryResponse getAllEntries(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Entry> entries = entryRepository.findAll(pageable);
        List<Entry> entryList = entries.getContent();
        List<EntryDTO> content = entryList.stream().map(this::mapEntryToDTO).toList();
        EntryResponse entryResponse = new EntryResponse();
        entryResponse.setContent(content);
        entryResponse.setPageNumber(entries.getNumber());
        entryResponse.setPageSize(entries.getSize());
        entryResponse.setTotalEntries(entries.getTotalElements());
        entryResponse.setTotalPages(entries.getTotalPages());
        entryResponse.setHasNext(entries.hasNext());
        return entryResponse;
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
