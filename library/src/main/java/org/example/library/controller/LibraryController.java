package org.example.library.controller;

import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.request.ArticleRequest;
import org.example.library.request.BeReadRequest;
import org.example.library.request.ReadRequest;
import org.example.library.request.UserRequest;
import org.example.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
public class LibraryController {

  final LibraryService libraryService;

  public LibraryController(LibraryService libraryService) {
    this.libraryService = libraryService;
  }

  @RequestMapping(value = "/dailyTop", method = RequestMethod.GET)
  public ResponseEntity<List<Article>> getDailyTopArticles() {
    return ResponseEntity.ok(libraryService.getDailyTopArticles());
  }

  @RequestMapping(value = "/weeklyTop", method = RequestMethod.GET)
  public ResponseEntity<List<Article>> getWeeklyTopArticles() {
    return ResponseEntity.ok(libraryService.getWeeklyTopArticles());
  }

  @RequestMapping(value = "/monthlyTop", method = RequestMethod.GET)
  public ResponseEntity<List<Article>> getMonthlyTopArticles() {
    return ResponseEntity.ok(libraryService.getMonthlyTopArticles());
  }

  @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
  public ResponseEntity<List<User>> searchUser(@RequestBody UserRequest userRequest) {
    return ResponseEntity.ok(libraryService.searchUser(userRequest));
  }

  @RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
  public ResponseEntity<List<Article>> searchArticle(@RequestBody ArticleRequest articleRequest) {
    return ResponseEntity.ok(libraryService.searchArticle(articleRequest));
  }

  @RequestMapping(value = "/searchUserRead", method = RequestMethod.POST)
  public ResponseEntity<List<ReadDetail>> searchUserRead(@RequestBody ReadRequest readRequest) {
    return ResponseEntity.ok(libraryService.searchUserRead(readRequest));
  }

  @RequestMapping(value = "/searchArticleBeRead", method = RequestMethod.POST)
  public ResponseEntity<List<ReadDetail>> searchArticleBeRead(
      @RequestBody BeReadRequest beReadRequest) {
    return ResponseEntity.ok(libraryService.searchArticleBeRead(beReadRequest));
  }

  @RequestMapping(value = "/picture/{pictureName}", method = RequestMethod.GET)
  public void queryPicture(@PathVariable String pictureName, HttpServletResponse response) {
    try {
      response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
      response.setHeader("Content-Disposition", "attachment; filename=" + pictureName);
      libraryService.queryPicture(pictureName, response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
  }

  @RequestMapping(value = "/video/{videoName}", method = RequestMethod.GET)
  public void queryVideo(@PathVariable String videoName, HttpServletResponse response) {
    try {
      response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
      response.setHeader("Content-Disposition", "attachment; filename=" + videoName);
      libraryService.queryVideo(videoName, response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
  }

  @RequestMapping(value = "/hdfs", method = RequestMethod.GET)
  public ResponseEntity<String> pingHDFS() {
    try {
      return ResponseEntity.ok(libraryService.pingHDFS());
    } catch (IOException e) {
      return ResponseEntity.ok("fail");
    }
  }
}
