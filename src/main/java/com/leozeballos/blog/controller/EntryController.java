package com.leozeballos.blog.controller;

import com.leozeballos.blog.dto.EntryDTO;
import com.leozeballos.blog.dto.EntryResponse;
import com.leozeballos.blog.exception.DuplicatedEntryException;
import com.leozeballos.blog.service.EntryService;
import com.leozeballos.blog.utility.AppConstants;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/" + AppConstants.API_VERSION + "/entries")
public class EntryController {

    private final EntryService entryService;

    @GetMapping
    public EntryResponse getAllEntries(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
        return entryService.getAllEntries(pageNumber, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDTO> getEntryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(entryService.getEntryById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveEntry(@Valid @RequestBody EntryDTO entryDTO) {
        try {
            EntryDTO dto = entryService.newEntry(entryDTO);
            return ResponseEntity.created(new java.net.URI("/api/" + AppConstants.API_VERSION + "/entries/" + dto.getId())).body(dto);
        } catch (DuplicatedEntryException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryDTO> updateEntry(@Valid @RequestBody EntryDTO entryDTO, @PathVariable(name = "id") Long id) {
        EntryDTO responseEntry = entryService.updateEntry(entryDTO, id);
        return new ResponseEntity<>(responseEntry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable(name = "id") Long id) {
        entryService.deleteEntry(id);
        return new ResponseEntity<>("Entry deleted", HttpStatus.OK);
    }

}
