package org.example.library.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.entity.Article;
import org.example.library.entity.BeReadDetail;

@Data
@NoArgsConstructor
public class ArticleDetail {

  private String aid;

  private String timestamp;

  private String title;

  private String category;

  private String articleAbstract;

  private String tags;

  private String authors;

  private String language;

  private String text;

  private String image;

  private String video;

  private Integer readNum;

  private Integer agreeNum;

  private Integer commentNum;

  private Integer shareNum;

  public ArticleDetail(Article article) {
    aid = article.getAid();
    timestamp = article.getTimestamp();
    title = article.getTitle();
    category = article.getCategory();
    articleAbstract = article.getArticleAbstract();
    tags = article.getArticleTags();
    authors = article.getAuthors();
    language = article.getLanguage();
    text = article.getText();
    image = article.getImage();
    video = article.getVideo();
  }

  public void setBeReadDetail(BeReadDetail beReadDetail) {
    readNum = beReadDetail.getReadNum();
    agreeNum = beReadDetail.getAgreeNum();
    commentNum = beReadDetail.getCommentNum();
    shareNum = beReadDetail.getShareNum();
  }
}
