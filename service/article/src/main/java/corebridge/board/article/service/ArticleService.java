package corebridge.board.article.service;

import corebridge.board.article.model.dto.ArticleDto;
import corebridge.board.article.model.entity.Article;
import corebridge.board.article.repository.ArticleRepository;
import corebridge.board.common.snowflake.Snowflake;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    @Transactional
    public ArticleDto.ArticleResponse create(ArticleDto.ArticleCreateRequest request) {
        Article article = articleRepository.save(
                Article.create(snowflake.nextId(), request.getTitle(), request.getContent(), request.getBoardId(), request.getWriterId())
        );

        return ArticleDto.ArticleResponse.from(article);
    }

    @Transactional
    public ArticleDto.ArticleResponse update(Long articleId, ArticleDto.ArticleUpdateRequest request) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.update(request.getTitle(), request.getContent());

        return ArticleDto.ArticleResponse.from(article);
    }

    @Transactional
    public ArticleDto.ArticleResponse read(Long articleId) {
        return ArticleDto.ArticleResponse.from(articleRepository.findById(articleId).orElseThrow());
    }

    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
