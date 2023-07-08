package com.leozeballos.blog.controller;

import com.leozeballos.blog.dto.CommentDTO;
import com.leozeballos.blog.service.CommentService;
import com.leozeballos.blog.utility.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/" + AppConstants.API_VERSION + "/entries")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{entryId}/comments")
    public List<CommentDTO> getCommentsByEntryId(@PathVariable(name = "entryId") Long entryId) {
        return commentService.getCommentsByEntryId(entryId);
    }

    @GetMapping("/{entryId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable(name = "entryId") Long entryId,
            @PathVariable(name = "commentId") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(entryId, commentId), HttpStatus.OK);
    }

    @PostMapping("/{entryId}/comments")
    public ResponseEntity<CommentDTO> saveComment(
            @PathVariable(value = "entryId") Long entryId,
            @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.newComment(entryId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{entryId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable(value = "entryId") Long entryId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.updateComment(entryId, commentId, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{entryId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "entryId") Long entryId,
            @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(entryId, commentId);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }

}
