package com.leozeballos.apirestspringboot.controller;

import com.leozeballos.apirestspringboot.dto.EntryDTO;
import com.leozeballos.apirestspringboot.dto.EntryResponse;
import com.leozeballos.apirestspringboot.service.EntryService;
import com.leozeballos.apirestspringboot.utility.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/" + AppConstants.API_VERSION + "/entries")
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

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
    public ResponseEntity<EntryDTO> saveEntity(@Valid @RequestBody EntryDTO entryDTO) {
        return new ResponseEntity<>(entryService.newEntry(entryDTO), HttpStatus.CREATED);
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
