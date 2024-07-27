package tipit.tipit.domain.article.dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import tipit.tipit.domain.article.entity.Category;
import tipit.tipit.domain.article.entity.SubCategory;

@Getter
@Setter
public class ArticleDto {
    private Long userId;
    private Boolean isComment;
    private Category category;
    private Boolean commentPermission;
    private Integer commentCount;
    private String content;
    private Long parentArticle;
    private Integer scrapCount;
    private List<SubCategory> subCategories;
}
