package org.example.library.vo;

import lombok.Data;

@Data
public class Comment {

  private String uid;

  private String userName;

  private String timestamp;

  private String region;

  private String category;

  private String readTimeLength;

  private String agreeOrNot;

  private String commentOrNot;

  private String commentDetail;

  private String shareOrNot;
}
