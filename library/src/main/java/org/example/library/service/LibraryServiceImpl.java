package org.example.library.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.library.entity.Article;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.hdfs.HDFSManager;
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

  private final HDFSManager hdfsManager;

  public LibraryServiceImpl(HDFSManager hdfsManager) {
    this.hdfsManager = hdfsManager;
  }

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
  public void queryPicture(String pictureName, OutputStream outputStream) {
    try {
      try (InputStream inputStream =
          hdfsManager.getFileInputStream("/articles/articles/article201/image_a201_0.jpg")) {
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private InputStream getPictureInputStream(String pictureName) throws FileNotFoundException {
    File file = new File("/tmp/resource/85.jpg");
    return new FileInputStream(file);
  }

  @Override
  public void queryVideo(String videoName, OutputStream outputStream) {
    try {
      try (InputStream inputStream =
          hdfsManager.getFileInputStream("/articles/articles/article201/video_a201_video.mp4")) {
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String pingHDFS() throws IOException {
    return hdfsManager.createFile();
  }

  private InputStream getVideoInputStream(String videoName) throws FileNotFoundException {
    File file = new File("/tmp/resource/video1.mp4");
    return new FileInputStream(file);
  }
}
