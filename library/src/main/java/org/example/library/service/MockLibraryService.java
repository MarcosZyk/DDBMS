package org.example.library.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.library.vo.ArticleDetail;
import org.example.library.vo.ArticleView;
import org.example.library.vo.Comment;
import org.example.library.vo.ReadingInfo;
import org.example.library.vo.UserDetail;
import org.example.library.vo.UserView;
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
public class MockLibraryService implements LibraryService {

  private final ArticleView articleView;
  private final ArticleDetail articleDetail;
  private final UserView userView;
  private final UserDetail userDetail;
  private final Comment comment;
  private final ReadingInfo readingInfo;

  public MockLibraryService() {
    articleView = new ArticleView();
    articleView.setAid("a1");
    articleView.setTitle("Mock Article");
    articleView.setCategory("mock category");
    articleView.setTags("mock tag");

    articleDetail = new ArticleDetail();
    articleDetail.setAid("a1");
    articleDetail.setTitle("Mock Article");
    articleDetail.setCategory("mock category");
    articleDetail.setTags("mock tag");
    articleDetail.setImage("image_1.jpg");
    articleDetail.setVideo("video_1.jpg");

    userView = new UserView();
    userView.setUid("u1");
    userView.setName("Mock user");
    userView.setPreferTags("mock tag");

    userDetail = new UserDetail();
    userDetail.setUid("u1");
    userDetail.setName("Mock user");
    userDetail.setPreferTags("mock tag");

    comment = new Comment();
    comment.setUid("u1");
    comment.setUserName("Mock user");
    comment.setCommentDetail("mock comment");

    readingInfo = new ReadingInfo();
    readingInfo.setAid("a1");
    readingInfo.setTitle("Mock Article");
    readingInfo.setCategory("mock category");
    readingInfo.setCommentDetail("mock comment");
  }

  @Override
  public List<ArticleView> getDailyTopArticles() {
    return Collections.singletonList(articleView);
  }

  @Override
  public List<ArticleView> getYearlyTopArticles() {
    return Collections.singletonList(articleView);
  }

  @Override
  public List<ArticleView> getMonthlyTopArticles() {
    return Collections.singletonList(articleView);
  }

  @Override
  public ArticleDetail getArticle(String aid) {
    return articleDetail;
  }

  @Override
  public List<Comment> getComments(String aid) {
    return Collections.singletonList(comment);
  }

  @Override
  public List<ArticleView> getSimilarArticles(String category, String tags) {
    return Collections.singletonList(articleView);
  }

  @Override
  public List<UserView> getPossibleUsers(String tags) {
    return Collections.singletonList(userView);
  }

  @Override
  public UserDetail getUser(String uid) {
    return userDetail;
  }

  @Override
  public List<ReadingInfo> getReadingInfos(String uid) {
    return Collections.singletonList(readingInfo);
  }

  @Override
  public List<ArticleView> getPossibleArticles(String tags) {
    return Collections.singletonList(articleView);
  }

  @Override
  public void queryPicture(String pictureName, OutputStream outputStream) {
    try {
      try (InputStream inputStream = getPictureInputStream()) {
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private InputStream getPictureInputStream() throws FileNotFoundException {
    File file = new File("E:\\Homework\\DDBMS\\resource\\85.jpg");
    return new FileInputStream(file);
  }

  @Override
  public void queryVideo(String videoName, OutputStream outputStream) {
    try {
      try (InputStream inputStream = getVideoInputStream()) {
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String pingHDFS() throws IOException {
    return "This is mock.";
  }

  private InputStream getVideoInputStream() throws FileNotFoundException {
    File file = new File("E:\\Homework\\DDBMS\\resource\\video1.mp4");
    return new FileInputStream(file);
  }
}
