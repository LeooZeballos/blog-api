package com.leozeballos.blog.service;

import com.leozeballos.blog.dto.EntryDTO;
import com.leozeballos.blog.dto.EntryResponse;

public interface EntryService {
    
    /**
     * Creates a new Entry from the given EntryDTO and returns it
     * 
     * @param entryDTO the EntryDTO to be mapped to Entry and saved
     * @return the EntryDTO mapped from the saved Entry
     */
    EntryDTO newEntry(EntryDTO entryDTO);

    /**
     * Gets all Entries from the database and returns them in an EntryResponse object
     * 
     * @param pageNumber the page number to be returned
     * @param pageSize  the page size to be returned
     * @param sortBy   the field to be sorted by
     * @param sortDir the direction to be sorted by
     * @return an EntryResponse object with the given parameters
     */
    EntryResponse getAllEntries(int pageNumber, int pageSize, String sortBy, String sortDir);

    /**
     * Gets an Entry by its id and returns it.
     * 
     * @param id the id of the Entry to be returned
     * @return the EntryDTO mapped from the Entry with the given id
     */
    EntryDTO getEntryById(Long id);

    /**
     * Updates an Entry with the given EntryDTO and returns it
     * 
     * @param entryDTO the EntryDTO to be mapped to Entry and saved
     * @param id the id of the Entry to be updated
     * @return the EntryDTO mapped from the updated Entry
     */
    EntryDTO updateEntry(EntryDTO entryDTO, Long id);

    /**
     * Deletes an Entry with the given id
     * 
     * @param id the id of the Entry to be deleted
     */
    void deleteEntry(Long id);

}
