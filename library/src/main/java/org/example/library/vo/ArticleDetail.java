package org.example.library.vo;

import lombok.Data;

@Data
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
}
