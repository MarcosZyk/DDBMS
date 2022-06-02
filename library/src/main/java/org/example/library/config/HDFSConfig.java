package org.example.library.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Configuration
public class HDFSConfig {

  private final String hdfsPath = "hdfs://172.21.0.2:9000";

  @Bean
  public org.apache.hadoop.conf.Configuration getConfiguration() {
    org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
    configuration.set("fs.defaultFS", hdfsPath);
    return configuration;
  }

  @Bean
  public FileSystem getFileSystem() {
    FileSystem fileSystem = null;
    try {
      String hdfsName = "root";
      fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), hdfsName);
    } catch (IOException | URISyntaxException | InterruptedException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return fileSystem;
  }
}
