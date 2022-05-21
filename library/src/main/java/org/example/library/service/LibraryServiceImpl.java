package org.example.library.service;

import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.request.ArticleRequest;
import org.example.library.request.BeReadRequest;
import org.example.library.request.ReadRequest;
import org.example.library.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
  @Override
  public List<Article> getDailyTopArticles() {
    return Collections.emptyList();
  }

  @Override
  public List<Article> getWeeklyTopArticles() {
    return Collections.emptyList();
  }

  @Override
  public List<Article> getMonthlyTopArticles() {
    return Collections.emptyList();
  }

  @Override
  public List<User> searchUser(UserRequest userRequest) {
    return Collections.emptyList();
  }

  @Override
  public List<Article> searchArticle(ArticleRequest articleRequest) {
    return Collections.emptyList();
  }

  @Override
  public List<ReadDetail> searchUserRead(ReadRequest readRequest) {
    return Collections.emptyList();
  }

  @Override
  public List<ReadDetail> searchArticleBeRead(BeReadRequest beReadRequest) {
    return Collections.emptyList();
  }
}
