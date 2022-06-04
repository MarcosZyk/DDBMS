package org.example.library.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.entity.Article;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleView {

  private String aid;

  private String title;

  private String category;

  private String tags;

  public ArticleView(Article article) {
    aid = article.getAid();
    title = article.getTitle();
    category = article.getCategory();
    tags = article.getArticleTags();
  }
}
