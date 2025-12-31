package corebridge.board.like.model.dto;

import corebridge.board.like.model.entity.ArticleLike;
import corebridge.board.like.repository.ArticleLikeRepository;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ArticleLikeDto {
    @Builder
    @Getter
    public static class ArticleLikeResponse {
        private Long articleLikeId;
        private Long articleId;
        private Long userId;
        private LocalDateTime createdAt;

        public static ArticleLikeResponse from(ArticleLike articleLike) {
            return ArticleLikeResponse.builder()
                    .articleLikeId(articleLike.getArticleLikeId())
                    .articleId(articleLike.getArticleId())
                    .userId(articleLike.getUserId())
                    .createdAt(articleLike.getCreatedAt())
                    .build();
        }
    }

}
