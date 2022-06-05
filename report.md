# DDBMS 课程项目报告

## 摘要

zhm

## 背景与动机

zhm

## 问题定义

zhm

## 相关解决方案

### MySQL Cluster

zhm

### MongoDB Sharding

zhm

### Hadoop Distributed File System

HDFS (Hadoop Distributed File System) 是基于Google发布的GFS论文设计开发，可以运行在通用硬件上的分布式文件系统。除了具备其他分布式文件系统相同的特性外，HDFS还具备如下的特有的特性：

1. 高容错性：HDFS的设计理念即认为硬件总是不可靠的，因此充分考虑了硬件故障情况。
2. 高吞吐量：HDFS支持高通量、流式数据访问。
3. 大文件存储：HDFS支持TB级乃至PB级规模的数据存储。

HDFS的架构包含三个主要部分：

1. NameNode：用于存储、生成文件系统的元数据。
2. DataNode：用于存储实际的数据，运行时会将自己管理的数据块上报给NameNode。
3. Client：支持业务访问HDFS，从NameNode、DataNode获取数据返回给业务端程序。

![HDFS_Architecture](E:\Homework\DDBMS\project\DDBMS\figures\HDFS_Architecture.png)

从HDFS的架构特性中可以看到，HDFS针对大规模、非结构化数据的管理可以提供十分优秀的支持效果。但是对于细粒度的、结构化的数据的管理，其运行机制存在很多累赘之处，使得性能难以达到生产级的要求。

### Redis

Redis是目前最流行的NoSQL数据库之一，是一款包含多种数据结构、支持网络、基于内存、可选持久性的键值对存储数据库。Redis具备如下特性：

1. 采用key-value型存储引擎
2. 支持分布式部署，具备高可扩展性
3. 基于内存运行，性能高效
4. 采用单进程单线程模型的同时支持高并发读写

![redis_process](E:\Homework\DDBMS\project\DDBMS\figures\redis_process.png)

从Redis的特性可以看出，Redis针对热点数据的缓存可以提供十分优秀的支持，但是其持久化的特性也仅局限于键值型数据的管理，对于关系型的交易事务、结构化的数据并不能提供性能充分的持久化管理。



## 方案描述

### 系统架构

zhm

### 模块描述

#### MongoDB Sharding

zhm

#### HDFS

依据HDFS的特性，系统采用HDFS来存储非结构化的大文件，主要支撑业务中的图片和视频访问。HDFS的部署方案为一个NameNode和三个dataNode，HDFS中存储的文件均以三副本的方式支持容错性。

考虑到HDFS的数据读写方式，客户端若部署在宿主机中且仅以端口映射的方式访问docker容器中的hdfs集群，则由于NameNode会返回一个dataNode的IP地址给客户端，客户端将无法通过docker虚拟网络的IP地址访问指定的dataNode。因此在部署时，将HDFS集群的容器与App程序的容器共同接入同一个docker网络，这样App程序即可通过IP地址访问dataNode并读写数据。这也使得系统的其他组件的容器也将被接入这个网络。

为了将宿主机内生成的数据上传到HDFS集群的容器中，部署时采用卷映射的方式将宿主机的目录映射到容器内的某个目录，然后在容器内执行HDFS的文件上传命令，将映射目录里的数据上传到HDFS上。

#### Redis

基于Redis的特性，系统采用Redis实现结构化数据的缓存功能。得益于spring data良好的生态，以springBoot为主要技术栈搭建的服务端程序可以很容易地集成Redis程序并自动实现缓存访问逻辑。

由于内存中的结构化数据均以对象的方式进行存储和使用，因此在缓存只redis时需要做数据类型上的转换和处理。在使用redis缓存时，以mongodb的访问类加上具体函数名作为缓存键，以对象序列化出的Json格式的字符串作为缓存值，这样即可支持任意单个对象以及对象数据的缓存和访问。

#### App

系统采用SpringBoot实现服务端程序，整合MongoDB、HDFS以及Redis，向前端提供数据访问与页面访问服务。

采用经典的Html5、Css3和JavaScript语言以及轻量级的BootStrap框架实现浏览器端的web页面。

结合系统数据的特点，我们为App程序做如下的功能设计。由于数据主要为文章的具体信息、读者的具体信息以及读者对文章阅读和评论等记录，因此可以假设该数据应源于类似公众号或图书馆运营性质的平台，主要向管理员提供多角度的文章与读者视图，以支持管理员更好地进行文章推送的分析与决策。因此定义如下功能：

1. 文章热榜功能，包括日榜、月榜和年榜
2. 文章详细信息查阅功能，支持查看文章所有的详细信息以及评论信息
3. 读者详细信息查阅功能，支持查看读者所有的详细信息以及阅读记录
4. 相似文章发掘功能，支持查询相同类别和标签的文章
5. 目标读者发掘功能，基于文章标签推测哪些读者会喜欢这篇文章，即支持按标签查询读者
6. 文章推荐功能，基于读者喜欢的标签进行文章查询，作为向该读者推荐的结果



## 方案评估

### 功能展示

以下图片展示了上文所描述的App功能。



### 性能评估

| 功能                                     | 数据量 | 总耗时 |
| ---------------------------------------- | ------ | ------ |
| HDFS上传图片、视频和文本数据             | 10G    | 1h     |
| MongoDB导入结构化数据                    | 265M   | 1min   |
| App页面信息访问速度                      | 10K    | 0.75s  |
| redis缓存命中情况下的App页面信息访问速度 | 10K    | 0.25s  |



## 结论与未来展望





## 参考文献

Docker (https://www.docker.com/)

Redis (https://redis.io/)

MongoDB (https://www.mongodb.com/1)

MongoDB Compass (https://www.mongodb.com/products/compass)

MySQL (https://www.mysql.com/)

Hadoop HDFS (https://hadoop.apache.org/)