package tipit.tipit.domain.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tipit.tipit.domain.article.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
