package org.example.library.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class HDFSManager {

  private static final String hdfsDirPath = "/hdfs-test/";
  private static final String hdfsFilePath = hdfsDirPath + "wordCountTest.txt";
  private static final String hdfsFileContent = "HELLO HDFS";
  private static final String uploadFilePath = "/user/Lenovo/pageRankTest.txt";

  private static final String projectDirPath = "E:\\Homework\\HDFSDemo\\";
  private static final String localDirPath = projectDirPath + "testFile\\";
  private static final String localFilePath = localDirPath + "pageRankTest.txt";
  private static final String fetchFilePath = localDirPath + "fetched.txt";

  @Autowired private FileSystem fs;

  public void mkdir() throws IOException {
    Path path = new Path(hdfsDirPath);
    fs.mkdirs(path);
    System.out.println(fs.getFileStatus(path));
  }

  public String createFile() throws IOException {
    Path path = new Path(hdfsFilePath);
    System.out.println(path);
    FSDataOutputStream outputStream = fs.create(path);
    byte[] buff = hdfsFileContent.getBytes();
    outputStream.write(buff, 0, buff.length);
    outputStream.hflush();
    System.out.println(fs.getFileStatus(path));
    return fs.getFileStatus(path).toString();
  }

  public void readFile() throws IOException {
    FSDataInputStream in = fs.open(new Path(hdfsFilePath));
    byte[] b = new byte[1024];
    int numBytes;
    System.out.print("Read from file: ");
    while ((numBytes = in.read(b)) > 0) {
      System.out.print(new String(Arrays.copyOf(b, numBytes)));
    }
    System.out.println();
    in.close();
  }

  public void deleteFile() throws IOException {
    Path path = new Path(hdfsFilePath);
    fs.delete(path, true);
    System.out.println(fs.exists(path));
  }

  public void copyToHDFS() throws IOException {
    Path src = new Path(localFilePath);
    Path dst = new Path(uploadFilePath);
    fs.copyFromLocalFile(src, dst);
    System.out.println(fs.getFileStatus(dst));
  }

  public void copyFromHDFS() throws IOException {
    Path src = new Path(uploadFilePath);
    Path dst = new Path(fetchFilePath);
    fs.copyToLocalFile(src, dst);
    File fetchedFile = new File(fetchFilePath);
    System.out.println(fetchedFile.exists());
  }
}
