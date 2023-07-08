package com.leozeballos.blog.service;

import com.leozeballos.blog.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO newComment(Long entryId, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByEntryId(Long entryId);
    CommentDTO getCommentById(Long entryId, Long commentId);
    CommentDTO updateComment(Long entryId, Long commentId, CommentDTO commentDTO);
    void deleteComment(Long entryId, Long commentId);
}
