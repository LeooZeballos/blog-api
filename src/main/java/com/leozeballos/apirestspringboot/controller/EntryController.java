package com.leozeballos.apirestspringboot.controller;

import com.leozeballos.apirestspringboot.dto.EntryDTO;
import com.leozeballos.apirestspringboot.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entries")
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ResponseEntity<EntryDTO> saveEntity(@RequestBody EntryDTO entryDTO) {
        return new ResponseEntity<>(entryService.newEntry(entryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<EntryDTO> getAllEntries() {
        return entryService.getAllEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDTO> getEntryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(entryService.getEntryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryDTO> updateEntry(@RequestBody EntryDTO entryDTO, @PathVariable(name = "id") Long id) {
        EntryDTO responseEntry = entryService.updateEntry(entryDTO, id);
        return new ResponseEntity<>(responseEntry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable(name = "id") Long id) {
        entryService.deleteEntry(id);
        return new ResponseEntity<>("Entry deleted", HttpStatus.OK);
    }

}
