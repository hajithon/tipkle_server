package tipit.tipit.domain.article.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tipit.tipit.domain.article.entity.Article;
import tipit.tipit.domain.article.repository.ArticleRepository;
import tipit.tipit.domain.user.entity.User;
import tipit.tipit.domain.user.repository.UserRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }
}
