package org.example.library.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.library.hdfs.HDFSManager;
import org.example.library.vo.ArticleDetail;
import org.example.library.vo.ArticleView;
import org.example.library.vo.Comment;
import org.example.library.vo.ReadingInfo;
import org.example.library.vo.UserDetail;
import org.example.library.vo.UserView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

// @Service
public class LibraryServiceImpl implements LibraryService {

  private final HDFSManager hdfsManager;

  public LibraryServiceImpl(HDFSManager hdfsManager) {
    this.hdfsManager = hdfsManager;
  }

  @Override
  public List<ArticleView> getDailyTopArticles() {
    return Collections.emptyList();
  }

  @Override
  public List<ArticleView> getYearlyTopArticles() {
    return Collections.emptyList();
  }

  @Override
  public List<ArticleView> getMonthlyTopArticles() {
    return Collections.emptyList();
  }

  @Override
  public ArticleDetail getArticle(String aid) {
    return new ArticleDetail();
  }

  @Override
  public List<Comment> getComments(String aid) {
    return Collections.emptyList();
  }

  @Override
  public List<ArticleView> getSimilarArticles(String category, String tags) {
    return Collections.emptyList();
  }

  @Override
  public List<UserView> getPossibleUsers(String tags) {
    return Collections.emptyList();
  }

  @Override
  public UserDetail getUser(String uid) {
    return new UserDetail();
  }

  @Override
  public List<ReadingInfo> getReadingInfos(String uid) {
    return Collections.emptyList();
  }

  @Override
  public List<ArticleView> getPossibleArticles(String tags) {
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
