package tipit.tipit.domain.article.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tipit.tipit.domain.article.entity.Article;
import tipit.tipit.domain.article.service.ArticleService;
import tipit.tipit.domain.article.dto.ArticleDto;
import tipit.tipit.domain.user.entity.User;

@RestController
@RequestMapping("/write/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDto articleDto) {
        if (articleDto.getSubCategories().size() > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        User user = articleService.getUserById(articleDto.getUserId());

        Article article = Article.builder()
                .user(user)
                .isComment(articleDto.getIsComment())
                .category(articleDto.getCategory())
                .commentPermission(articleDto.getCommentPermission())
                .commentCount(articleDto.getCommentCount())
                .content(articleDto.getContent())
                .parentArticle(articleDto.getParentArticle())
                .scrapCount(articleDto.getScrapCount())
                .subCategories(articleDto.getSubCategories())
                .build();

        Article savedArticle = articleService.saveArticle(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }
}
