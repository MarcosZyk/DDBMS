package org.example.library.controller;

import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.request.ArticleRequest;
import org.example.library.request.BeReadRequest;
import org.example.library.request.ReadRequest;
import org.example.library.request.UserRequest;
import org.example.library.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibraryController {

  final LibraryService libraryService;

  public LibraryController(LibraryService libraryService) {
    this.libraryService = libraryService;
  }

  public ResponseEntity<List<Article>> getDailyTopArticles() {
    return ResponseEntity.ok(libraryService.getDailyTopArticles());
  }

  public ResponseEntity<List<Article>> getWeeklyTopArticles() {
    return ResponseEntity.ok(libraryService.getWeeklyTopArticles());
  }

  public ResponseEntity<List<Article>> getMonthlyTopArticles() {
    return ResponseEntity.ok(libraryService.getMonthlyTopArticles());
  }

  public ResponseEntity<List<User>> searchUser(@RequestBody UserRequest userRequest) {
    return ResponseEntity.ok(libraryService.searchUser(userRequest));
  }

  public ResponseEntity<List<Article>> searchArticle(@RequestBody ArticleRequest articleRequest) {
    return ResponseEntity.ok(libraryService.searchArticle(articleRequest));
  }

  public ResponseEntity<List<ReadDetail>> searchUserRead(@RequestBody ReadRequest readRequest) {
    return ResponseEntity.ok(libraryService.searchUserRead(readRequest));
  }

  public ResponseEntity<List<ReadDetail>> searchArticleBeRead(
      @RequestBody BeReadRequest beReadRequest) {
    return ResponseEntity.ok(libraryService.searchArticleBeRead(beReadRequest));
  }

  public void queryPicture(@RequestParam String pictureName) {}

  public void queryVideo(@RequestParam String videoName) {}
}
