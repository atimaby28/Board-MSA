package corebridge.board.comment.api;

import corebridge.board.comment.model.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class CommentApiTest {
    RestClient restClient = RestClient.create("http://localhost:8001");

    @Test
    void create() {
        CommentDto.CommentResponse response1 = createComment(new CommentCreateRequest(1L, "My first comment", null, 1L));
        CommentDto.CommentResponse response2 = createComment(new CommentCreateRequest(1L, "My Second comment", response1.getParentCommentId(), 1L));
        CommentDto.CommentResponse response3 = createComment(new CommentCreateRequest(1L, "My Third comment", response1.getCommentId(), 1L));

        System.out.println("commentId=%s".formatted(response1.getCommentId()));
        System.out.println("\tcommentId=%s".formatted(response2.getCommentId()));
        System.out.println("\tcommentId=%s".formatted(response3.getCommentId()));

//        commentId=264057361404231680
//          commentId=264057361873993728
//          commentId=264057361907548160
    }

    CommentDto.CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentDto.CommentResponse.class);
    }

    @Test
    void read() {
        CommentDto.CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 264057361404231680L)
                .retrieve()
                .body(CommentDto.CommentResponse.class);

        System.out.println("response = " + response);
    }

//        commentId=264057361404231680
//          commentId=264057361873993728
//          commentId=264057361907548160
    @Test
    void delete() {
        restClient.delete()
                .uri("/v1/comments/{commentId}", 264057361404231680L)
                .retrieve();

        restClient.delete()
                .uri("/v1/comments/{commentId}", 264057361873993728L)
                .retrieve();

        restClient.delete()
                .uri("/v1/comments/{commentId}", 264057361907548160L)
                .retrieve();
    }

    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequest {
        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }
}
