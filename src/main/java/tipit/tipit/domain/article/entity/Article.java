package tipit.tipit.domain.article.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipit.tipit.domain.user.entity.User;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isComment;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Boolean commentPermission;

    private Integer commentCount;

    private String content;

    private Long parentArticle;

    private Integer scrapCount;

    @ElementCollection(targetClass = SubCategory.class)
    @Enumerated(EnumType.STRING)
    private List<SubCategory> subCategories;

    @Builder
    public Article(Long id, User user, Boolean isComment, Category category, Boolean commentPermission, Integer commentCount, String content, Long parentArticle, Integer scrapCount, List<SubCategory> subCategories) {
        this.id = id;
        this.user = user;
        this.isComment = isComment;
        this.category = category;
        this.commentPermission = commentPermission;
        this.commentCount = commentCount;
        this.content = content;
        this.parentArticle = parentArticle;
        this.scrapCount = scrapCount;
        this.subCategories = subCategories;
    }
}
