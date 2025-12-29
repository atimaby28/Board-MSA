package corebridge.board.comment.model.dto;

import corebridge.board.comment.model.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class CommentDto {

    @Builder
    @Getter
    public static class CommentCreateRequest {
        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }

    @Builder
    @Getter
    public static class CommentResponse {
        private Long commentId;
        private String content;
        private Long parentCommentId;
        private Long articleId;
        private Long writerId;
        private Boolean deleted;
        private LocalDateTime createdAt;

        public static CommentResponse from(Comment comment) {
            return CommentResponse.builder()
                    .commentId(comment.getCommentId())
                    .content(comment.getContent())
                    .parentCommentId(comment.getParentCommentId())
                    .articleId(comment.getArticleId())
                    .writerId(comment.getWriterId())
                    .deleted(comment.getDeleted())
                    .createdAt(comment.getCreatedAt())
                    .build();

        }
    }
}
