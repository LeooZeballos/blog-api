package com.leozeballos.apirestspringboot.service;

import com.leozeballos.apirestspringboot.dto.CommentDTO;
import com.leozeballos.apirestspringboot.entity.Comment;
import com.leozeballos.apirestspringboot.entity.Entry;
import com.leozeballos.apirestspringboot.exception.BlogAppException;
import com.leozeballos.apirestspringboot.exception.ResourceNotFoundException;
import com.leozeballos.apirestspringboot.repository.CommentRepository;
import com.leozeballos.apirestspringboot.repository.EntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final EntryRepository entryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, EntryRepository entryRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
    }

    private CommentDTO mapCommentToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    private Comment mapDTOtoComment(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    @Override
    public CommentDTO newComment(Long entryId, CommentDTO commentDTO) {
        Comment comment = mapDTOtoComment(commentDTO);
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "entryId", entryId));
        comment.setEntry(entry);
        comment = commentRepository.save(comment);
        return mapCommentToDTO(comment);
    }

    @Override
    public List<CommentDTO> getCommentsByEntryId(Long entryId) {
        List<Comment> comments = commentRepository.findByEntryId(entryId);
        return comments.stream().map(this::mapCommentToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long entryId, Long commentId) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "entryId", entryId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found", "commentId", commentId));
        if (comment.getEntry().getId().equals(entry.getId())) {
            return mapCommentToDTO(comment);
        } else {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the entry");
        }
    }

    @Override
    public CommentDTO updateComment(Long entryId, Long commentId, CommentDTO commentDTO) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "entryId", entryId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found", "commentId", commentId));
        if (comment.getEntry().getId().equals(entry.getId())) {
            comment.setComment(commentDTO.getComment());
            comment.setTitle(commentDTO.getTitle());
            comment.setEmail(commentDTO.getEmail());
            comment = commentRepository.save(comment);
            return mapCommentToDTO(comment);
        } else {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the entry");
        }
    }

    @Override
    public void deleteComment(Long entryId, Long commentId) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> new ResourceNotFoundException("Entry not found", "entryId", entryId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not found", "commentId", commentId));
        if (comment.getEntry().getId().equals(entry.getId())) {
            commentRepository.delete(comment);
        } else {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the entry");
        }
    }

}
