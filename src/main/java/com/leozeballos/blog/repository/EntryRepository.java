package com.leozeballos.blog.repository;

import com.leozeballos.blog.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    /**
    * Checks if an Entry with the given title exists
    *
    * @param title the title to be checked
    * @return true if an Entry with the given title exists, false otherwise
    */
    boolean existsByTitle(String title);
}
