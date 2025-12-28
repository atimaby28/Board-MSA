package corebridge.board.article.api;

import corebridge.board.article.model.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:8000");

    @Test
    void createTest() {
        ArticleDto.ArticleResponse response = create(new ArticleCreateRequest(
                "Hi, This is my first article.", "My contents", 1L, 1L
        ));

        System.out.println("response = " + response);
    }

    ArticleDto.ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleDto.ArticleResponse.class);
    }
    
    @Test
    void readTest() {
        ArticleDto.ArticleResponse response = read(263660216380063744L);
        System.out.println("response = " + response);
    }

    ArticleDto.ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleDto.ArticleResponse.class);
    }

    @Test
    void updateTest() {
        update(263660216380063744L);
        ArticleDto.ArticleResponse response = read(263660216380063744L);
        System.out.println("response = " + response);
    }

    ArticleDto.ArticleResponse update(Long articleId) {
        return restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("Hi, This is my second article.", "My second content"))
                .retrieve()
                .body(ArticleDto.ArticleResponse.class);
    }

    @Test
    void deleteTest() {
        restClient.delete()
                .uri("/v1/articles/{articleId}", 263660216380063744L)
                .retrieve();
    }

    @Builder
    @Getter
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Builder
    @Getter
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }
}
