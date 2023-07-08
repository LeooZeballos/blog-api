package com.leozeballos.blog.repository;

import com.leozeballos.blog.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
