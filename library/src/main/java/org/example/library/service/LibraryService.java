package org.example.library.service;

import org.example.library.vo.ArticleDetail;
import org.example.library.vo.ArticleView;
import org.example.library.vo.Comment;
import org.example.library.vo.ReadingInfo;
import org.example.library.vo.UserDetail;
import org.example.library.vo.UserView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface LibraryService {

  List<ArticleView> getDailyTopArticles();

  List<ArticleView> getWeeklyTopArticles();

  List<ArticleView> getMonthlyTopArticles();

  ArticleDetail getArticle(String aid);

  List<Comment> getComments(String aid);

  List<ArticleView> getSimilarArticles(String category, String tags);

  List<UserView> getPossibleUsers(String tags);

  UserDetail getUser(String uid);

  List<ReadingInfo> getReadingInfos(String uid);

  List<ArticleView> getPossibleArticles(String tags);

  void queryPicture(String pictureName, OutputStream outputStream);

  void queryVideo(String videoName, OutputStream outputStream);

  String pingHDFS() throws IOException;
}
