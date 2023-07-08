package com.leozeballos.blog.controller;

import com.leozeballos.blog.dto.EntryDTO;
import com.leozeballos.blog.dto.EntryResponse;
import com.leozeballos.blog.exception.DuplicatedEntryException;
import com.leozeballos.blog.exception.ResourceNotFoundException;
import com.leozeballos.blog.service.EntryService;
import com.leozeballos.blog.utility.AppConstants;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/" + AppConstants.API_VERSION + "/entries")
@Slf4j
public class EntryController {

    private final EntryService service;

    @GetMapping
    public EntryResponse getAllEntries(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
        return service.getAllEntries(pageNumber, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDTO> getEntryById(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(service.getEntryById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveEntry(
            @Valid @RequestBody EntryDTO entryDTO
    ) {
        try {
            EntryDTO dto = service.newEntry(entryDTO);
            log.info("Entry with id " + dto.getId() + " created successfully");
            return ResponseEntity.created(new java.net.URI("/api/" + AppConstants.API_VERSION + "/entries/" + dto.getId())).body(dto);
        } catch (DuplicatedEntryException e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEntry(
            @Valid @RequestBody EntryDTO entryDTO,
            @PathVariable(name = "id") Long id
    ) {
        try {
            EntryDTO updatedEntry = service.updateEntry(entryDTO, id);
            return ResponseEntity.ok(updatedEntry);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ValidationException e) {
            log.error("Validation failed for entry with ID {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while updating entry with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(
            @PathVariable(name = "id") Long id
    ) {
        service.deleteEntry(id);
        return ResponseEntity.ok("Entry with id " + id + " deleted successfully");
    }

}
