package org.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document("article")
public class Article {
  @Id private String mongoId;

  @Field("id")
  private String id;

  @Field("aid")
  private String aid;

  @Field("timestamp")
  private String timestamp;

  @Field("title")
  private String title;

  @Field("category")
  private String category;

  @Field("abstract")
  private String articleAbstract;

  @Field("articleTags")
  private String articleTags;

  @Field("authors")
  private String authors;

  @Field("language")
  private String language;

  @Field("text")
  private String text;

  @Field("image")
  private String image;

  @Field("video")
  private String video;
}
