package org.example.library.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library.entity.User;

@Data
@NoArgsConstructor
public class UserView {

  private String uid;

  private String name;

  private String preferTags;

  public UserView(User user) {
    uid = user.getUid();
    name = user.getName();
    preferTags = user.getPreferTags();
  }
}
