package org.example.library.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.entity.ReadDetail;

@Data
@NoArgsConstructor
public class Comment {

  private String uid;

  private String userName;

  private String timestamp;

  private Integer readTimeLength;

  private String agreeOrNot;

  private String commentOrNot;

  private String commentDetail;

  private String shareOrNot;

  public Comment(ReadDetail readDetail) {
    uid = readDetail.getUid();
    timestamp = readDetail.getTimestamp();
    readTimeLength = readDetail.getReadTimeLength();
    agreeOrNot = readDetail.getAgreeOrNot();
    commentOrNot = readDetail.getCommentOrNot();
    commentDetail = readDetail.getCommentDetail();
    shareOrNot = readDetail.getShareOrNot();
  }
}
