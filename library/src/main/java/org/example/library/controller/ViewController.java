package org.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

  @RequestMapping(value = "/index")
  public String getIndex() {
    return "index";
  }

  @RequestMapping(value = "/article")
  public String getArticle(@RequestParam String aid) {
    return "article";
  }

  @RequestMapping(value = "/user")
  public String getUser(@RequestParam String uid) {
    return "user";
  }
}
