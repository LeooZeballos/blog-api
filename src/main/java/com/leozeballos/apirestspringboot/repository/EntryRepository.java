package com.leozeballos.apirestspringboot.repository;

import com.leozeballos.apirestspringboot.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
