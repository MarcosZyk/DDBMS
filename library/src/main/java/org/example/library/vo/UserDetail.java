package org.example.library.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.entity.User;

@Data
@NoArgsConstructor
public class UserDetail {

  private String uid;

  private String timestamp;

  private String name;

  private String gender;

  private String email;

  private String phone;

  private String dept;

  private String grade;

  private String language;

  private String region;

  private String role;

  private String preferTags;

  private Integer obtainedCredits;

  public UserDetail(User user) {
    uid = user.getUid();
    timestamp = user.getTimestamp();
    name = user.getName();
    gender = user.getGender();
    email = user.getEmail();
    phone = user.getPhone();
    dept = user.getDept();
    grade = user.getGrade();
    language = user.getLanguage();
    region = user.getRegion();
    role = user.getRole();
    preferTags = user.getPreferTags();
    obtainedCredits = user.getObtainedCredits();
  }
}
