package org.example.library.controller;

import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

  @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
  public ResponseEntity<Article> getArticle(@RequestParam String aid) {
    return ResponseEntity.ok(libraryService.getArticle(aid));
  }

  @RequestMapping(value = "/getComments", method = RequestMethod.GET)
  public ResponseEntity<List<ReadDetail>> getComments(@RequestParam String aid) {
    return ResponseEntity.ok(libraryService.getComments(aid));
  }

  @RequestMapping(value = "/getSimilarArticles", method = RequestMethod.GET)
  public ResponseEntity<List<Article>> getSimilarArticles(
      @RequestParam String category, @RequestParam String tags) {
    return ResponseEntity.ok(libraryService.getSimilarArticles(category, tags));
  }

  @RequestMapping(value = "/getPossibleUsers", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getPossibleUsers(@RequestParam String tags) {
    return ResponseEntity.ok(libraryService.getPossibleUsers(tags));
  }

  @RequestMapping(value = "/getUser", method = RequestMethod.GET)
  public ResponseEntity<User> getUser(@RequestParam String uid) {
    return ResponseEntity.ok(libraryService.getUser(uid));
  }

  @RequestMapping(value = "/getReadingInfos", method = RequestMethod.GET)
  public ResponseEntity<List<ReadDetail>> getReadingInfos(@RequestParam String uid) {
    return ResponseEntity.ok(libraryService.getReadingInfos(uid));
  }

  @RequestMapping(value = "/getPossibleArticles", method = RequestMethod.GET)
  public ResponseEntity<List<Article>> getPossibleArticles(@RequestParam String tags) {
    return ResponseEntity.ok(libraryService.getPossibleArticles(tags));
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
