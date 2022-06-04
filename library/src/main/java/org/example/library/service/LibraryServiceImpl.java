package org.example.library.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.example.library.entity.Article;
import org.example.library.entity.BeReadDetail;
import org.example.library.entity.Rank;
import org.example.library.entity.ReadDetail;
import org.example.library.entity.User;
import org.example.library.hdfs.HDFSManager;
import org.example.library.mongo.ArticleRepository;
import org.example.library.mongo.BeReadDetailRepository;
import org.example.library.mongo.RankRepository;
import org.example.library.mongo.ReadDetailRepository;
import org.example.library.mongo.UserRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

  private final HDFSManager hdfsManager;

  private final UserRepository userRepository;

  private final ArticleRepository articleRepository;

  private final RankRepository rankRepository;

  private final ReadDetailRepository readDetailRepository;

  private final BeReadDetailRepository beReadDetailRepository;

  public LibraryServiceImpl(
      HDFSManager hdfsManager,
      UserRepository userRepository,
      ArticleRepository articleRepository,
      RankRepository rankRepository,
      ReadDetailRepository readDetailRepository,
      BeReadDetailRepository beReadDetailRepository) {
    this.hdfsManager = hdfsManager;
    this.userRepository = userRepository;
    this.articleRepository = articleRepository;
    this.rankRepository = rankRepository;
    this.readDetailRepository = readDetailRepository;
    this.beReadDetailRepository = beReadDetailRepository;
  }

  @Override
  public List<ArticleView> getDailyTopArticles() {
    return getTopArticles("daily");
  }

  @Override
  public List<ArticleView> getWeeklyTopArticles() {
    return getTopArticles("weekly");
  }

  @Override
  public List<ArticleView> getMonthlyTopArticles() {
    return getTopArticles("monthly");
  }

  private List<ArticleView> getTopArticles(String temporalGranularity) {
    List<Rank> rankList = rankRepository.findByTemporalGranularity(temporalGranularity);
    List<ArticleView> articleViewList = new ArrayList<>();
    List<String> articleIdList = rankList.get(0).getArticleAids();
    for (int i = 0; i < 15; i++) {
      Article article = articleRepository.findByAid(articleIdList.get(i));
      articleViewList.add(new ArticleView(article));
    }
    return articleViewList;
  }

  @Override
  public ArticleDetail getArticle(String aid) {
    Article article = articleRepository.findByAid(aid);
    ArticleDetail articleDetail = new ArticleDetail(article);
    BeReadDetail beReadDetail = beReadDetailRepository.findByAid(aid);
    articleDetail.setBeReadDetail(beReadDetail);
    return articleDetail;
  }

  @Override
  public List<Comment> getComments(String aid) {
    List<ReadDetail> readDetailList = readDetailRepository.findByAid(aid);
    List<Comment> commentList = new ArrayList<>();
    for (ReadDetail readDetail : readDetailList.subList(0, 20)) {
      Comment comment = new Comment(readDetail);
      commentList.add(comment);
      User user = userRepository.findByUid(readDetail.getUid());
      comment.setUserName(user.getName());
    }
    return commentList;
  }

  @Override
  public List<ArticleView> getSimilarArticles(String category, String tags) {
    List<Article> articleList = articleRepository.findByCategoryAndTag(category, tags);
    List<ArticleView> articleViewList = new ArrayList<>();
    for (Article article : articleList.subList(0, 10)) {
      articleViewList.add(new ArticleView(article));
    }
    return articleViewList;
  }

  @Override
  public List<UserView> getPossibleUsers(String tags) {
    List<User> userList = userRepository.findByPreferTagOrderByCredits(tags);
    return userList.stream().limit(10).map(UserView::new).collect(Collectors.toList());
  }

  @Override
  public UserDetail getUser(String uid) {
    return new UserDetail(userRepository.findByUid(uid));
  }

  @Override
  public List<ReadingInfo> getReadingInfos(String uid) {
    return readDetailRepository.findByUid(uid).stream()
        .limit(15)
        .map(
            o -> {
              ReadingInfo readingInfo = new ReadingInfo(o);
              Article article = articleRepository.findByAid(readingInfo.getAid());
              readingInfo.setTitle(article.getTitle());
              return readingInfo;
            })
        .collect(Collectors.toList());
  }

  @Override
  public List<ArticleView> getPossibleArticles(String tags) {
    return articleRepository.findByTag(tags).stream()
        .limit(20)
        .map(ArticleView::new)
        .collect(Collectors.toList());
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
