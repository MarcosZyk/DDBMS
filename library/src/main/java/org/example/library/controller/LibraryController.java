package org.example.library.controller;

import org.example.library.service.LibraryService;
import org.example.library.vo.ArticleDetail;
import org.example.library.vo.ArticleView;
import org.example.library.vo.Comment;
import org.example.library.vo.ReadingInfo;
import org.example.library.vo.UserDetail;
import org.example.library.vo.UserView;
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
  public ResponseEntity<List<ArticleView>> getDailyTopArticles() {
    return ResponseEntity.ok(libraryService.getDailyTopArticles());
  }

  @RequestMapping(value = "/monthlyTop", method = RequestMethod.GET)
  public ResponseEntity<List<ArticleView>> getMonthlyTopArticles() {
    return ResponseEntity.ok(libraryService.getMonthlyTopArticles());
  }

  @RequestMapping(value = "/yearlyTop", method = RequestMethod.GET)
  public ResponseEntity<List<ArticleView>> getYearlyTopArticles() {
    return ResponseEntity.ok(libraryService.getYearlyTopArticles());
  }

  @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
  public ResponseEntity<ArticleDetail> getArticle(@RequestParam String aid) {
    return ResponseEntity.ok(libraryService.getArticle(aid));
  }

  @RequestMapping(value = "/getComments", method = RequestMethod.GET)
  public ResponseEntity<List<Comment>> getComments(@RequestParam String aid) {
    return ResponseEntity.ok(libraryService.getComments(aid));
  }

  @RequestMapping(value = "/getSimilarArticles", method = RequestMethod.GET)
  public ResponseEntity<List<ArticleView>> getSimilarArticles(
      @RequestParam String category, @RequestParam String tags) {
    return ResponseEntity.ok(libraryService.getSimilarArticles(category, tags));
  }

  @RequestMapping(value = "/getPossibleUsers", method = RequestMethod.GET)
  public ResponseEntity<List<UserView>> getPossibleUsers(@RequestParam String tags) {
    return ResponseEntity.ok(libraryService.getPossibleUsers(tags));
  }

  @RequestMapping(value = "/getUser", method = RequestMethod.GET)
  public ResponseEntity<UserDetail> getUser(@RequestParam String uid) {
    return ResponseEntity.ok(libraryService.getUser(uid));
  }

  @RequestMapping(value = "/getReadingInfos", method = RequestMethod.GET)
  public ResponseEntity<List<ReadingInfo>> getReadingInfos(@RequestParam String uid) {
    return ResponseEntity.ok(libraryService.getReadingInfos(uid));
  }

  @RequestMapping(value = "/getPossibleArticles", method = RequestMethod.GET)
  public ResponseEntity<List<ArticleView>> getPossibleArticles(@RequestParam String tags) {
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
