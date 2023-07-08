package com.leozeballos.blog.repository;

import com.leozeballos.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEntryId(Long entryId);
}
