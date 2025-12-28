package corebridge.board.article.model.dto;

import corebridge.board.article.model.entity.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ArticleDto {
    @Builder
    @Getter
    public static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Builder
    @Getter
    public static class ArticleUpdateRequest {
        private String title;
        private String content;
    }

    @Builder
    @Getter
    public static class ArticleResponse {
        private Long articleId;
        private String title;
        private String content;
        private Long boardId;
        private Long writerId;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;

        public static ArticleDto.ArticleResponse from(Article article) {
            return ArticleResponse.builder()
                    .articleId(article.getArticleId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .boardId(article.getBoardId())
                    .writerId(article.getWriterId())
                    .createAt(article.getCreateAt())
                    .modifiedAt(article.getModifiedAt())
                    .build();
        }
    }
}
