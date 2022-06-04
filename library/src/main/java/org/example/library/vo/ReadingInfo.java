package org.example.library.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.entity.ReadDetail;

@Data
@NoArgsConstructor
public class ReadingInfo {

  private String aid;

  private String title;

  private String timestamp;

  private String region;

  private String category;

  private Integer readTimeLength;

  private String agreeOrNot;

  private String commentOrNot;

  private String commentDetail;

  private String shareOrNot;

  public ReadingInfo(ReadDetail readDetail) {
    aid = readDetail.getAid();
    timestamp = readDetail.getTimestamp();
    region = readDetail.getRegion();
    category = readDetail.getCategory();
    readTimeLength = readDetail.getReadTimeLength();
    agreeOrNot = readDetail.getAgreeOrNot();
    commentOrNot = readDetail.getCommentOrNot();
    commentDetail = readDetail.getCommentDetail();
    shareOrNot = readDetail.getShareOrNot();
  }
}
