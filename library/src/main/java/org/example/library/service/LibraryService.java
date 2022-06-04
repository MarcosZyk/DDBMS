package org.example.library.service;

import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface LibraryService {

  List<Article> getDailyTopArticles();

  List<Article> getWeeklyTopArticles();

  List<Article> getMonthlyTopArticles();

  Article getArticle(String aid);

  List<ReadDetail> getComments(String aid);

  List<Article> getSimilarArticles(String category, String tags);

  List<User> getPossibleUsers(String tags);

  User getUser(String uid);

  List<ReadDetail> getReadingInfos(String uid);

  List<Article> getPossibleArticles(String tags);

  void queryPicture(String pictureName, OutputStream outputStream);

  void queryVideo(String videoName, OutputStream outputStream);

  String pingHDFS() throws IOException;
}
