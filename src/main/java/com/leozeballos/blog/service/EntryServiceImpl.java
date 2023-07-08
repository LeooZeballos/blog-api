package com.leozeballos.blog.service;

import com.leozeballos.blog.dto.EntryDTO;
import com.leozeballos.blog.dto.EntryResponse;
import com.leozeballos.blog.entity.Entry;
import com.leozeballos.blog.exception.ResourceNotFoundException;
import com.leozeballos.blog.repository.EntryRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;
    private final ModelMapper modelMapper;

    @Override
    public EntryDTO newEntry(EntryDTO entryDTO) {
        // Map DTO to Entry and save it
        Entry entry = mapDTOtoEntry(entryDTO);
        entry = entryRepository.save(entry);
        return mapEntryToDTO(entry);
    }

    @Override
    public EntryResponse getAllEntries(int pageNumber, int pageSize, String sortBy, String sortDir) {
        // Sort ASC or DESC
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Get Page of Entries
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Entry> entries = entryRepository.findAll(pageable);
        List<Entry> entryList = entries.getContent();

        // Build EntryResponse object and return it
        EntryResponse entryResponse = EntryResponse.builder()
                .content(entryList.stream().map(this::mapEntryToDTO).collect(java.util.stream.Collectors.toList()))
                .pageNumber(entries.getNumber())
                .pageSize(entries.getSize())
                .totalEntries(entries.getTotalElements())
                .totalPages(entries.getTotalPages())
                .hasNext(entries.hasNext())
                .build();
        return entryResponse;
    }

    @Override
    public EntryDTO getEntryById(Long id) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "id", id));
        return mapEntryToDTO(entry);
    }

    @Override
    public EntryDTO updateEntry(EntryDTO entryDTO, Long id) {
        // Check if Entry exists
        if (!entryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entry not found", "id", id);
        }

        // Map DTO to Entry and save it
        Entry entry = mapDTOtoEntry(entryDTO);
        entry.setId(id);
        entry = entryRepository.save(entry);

        // Return DTO mapped from saved Entry
        return mapEntryToDTO(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        // Check if Entry exists and delete it
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "id", id));
        entryRepository.delete(entry);
    }

     /**
     * Map Entry to DTO
     * 
     * @param entry Entry to map
     * @return DTO mapped
     */
    private EntryDTO mapEntryToDTO(Entry entry) {
        return modelMapper.map(entry, EntryDTO.class);
    }

    /**
     * Map DTO to Entry
     * 
     * @param entryDTO DTO to map
     * @return Entry mapped
     */
    private Entry mapDTOtoEntry(EntryDTO entryDTO) {
        return modelMapper.map(entryDTO, Entry.class);
    }

}
