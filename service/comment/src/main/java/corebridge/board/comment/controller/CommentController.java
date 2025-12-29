package corebridge.board.comment.controller;

import corebridge.board.comment.model.dto.CommentDto;
import corebridge.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/v1/comments/{commentId}")
    public CommentDto.CommentResponse read(
            @PathVariable Long commentId) {
        return commentService.read(commentId);
    }

    @PostMapping("/v1/comments")
    public CommentDto.CommentResponse create(
            @RequestBody CommentDto.CommentCreateRequest request) {
        return commentService.create(request);
    }

    @DeleteMapping("/v1/comments/{commentId}")
    public void delete(
            @PathVariable Long commentId) {
        commentService.delete(commentId);
    }

}
