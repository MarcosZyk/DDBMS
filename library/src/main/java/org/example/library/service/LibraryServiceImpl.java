package org.example.library.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.request.ArticleRequest;
import org.example.library.request.BeReadRequest;
import org.example.library.request.ReadRequest;
import org.example.library.request.UserRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

  @Override
  public void queryPicture(String pictureName, OutputStream outputStream) {}

  @Override
  public void queryVideo(String videoName, OutputStream outputStream) {
    try {
      try (InputStream inputStream = getVideoInputStream(videoName)) {
        IOUtils.copy(inputStream, outputStream);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private InputStream getVideoInputStream(String videoName) throws FileNotFoundException {
    File file = new File("E:\\Homework\\DDBMS\\video1.mp4");
    return new FileInputStream(file);
  }
}
