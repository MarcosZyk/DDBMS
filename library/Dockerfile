#版本1
FROM java:8
VOLUME /tmp
ADD target/library.jar library.jar
ENV TZ=GMT+8
RUN bash -c 'touch /library.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/library.jar"]
