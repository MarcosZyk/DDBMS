package org.example.library.service;

import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.request.ArticleRequest;
import org.example.library.request.BeReadRequest;
import org.example.library.request.ReadRequest;
import org.example.library.request.UserRequest;

import java.util.List;

public interface LibraryService {

  List<Article> getDailyTopArticles();

  List<Article> getWeeklyTopArticles();

  List<Article> getMonthlyTopArticles();

  List<User> searchUser(UserRequest userRequest);

  List<Article> searchArticle(ArticleRequest articleRequest);

  List<ReadDetail> searchUserRead(ReadRequest readRequest);

  List<ReadDetail> searchArticleBeRead(BeReadRequest beReadRequest);
}
