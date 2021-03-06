# DDBMS 部署方案

## 安装

需要安装以下环境

* Docker
* Java 8
* Maven
* [mongosh](https://www.mongodb.com/docs/mongodb-shell/install/)
* [MongoDB Database Tools](https://www.mongodb.com/docs/database-tools/installation/installation/)
* [Mongo Compass](https://www.mongodb.com/docs/compass/current/install/)



## 部署

### 数据生成脚本

* genTable_mongoDB10G.py：修改了脚本中的部分内容
  * 将 user 表的 obtainedCredits 字段和 read 表的 readTimeLength 字段修改为 int 类型
  * 在 read 表中添加了 region 和 category 字段，用于分片使用
  * 修改了视频文件类型

### 部署脚本

* setup.sh、setup.bat：初始化并启动容器，包括数据加载等，用于第一次启动
* shutdown.sh、shutdown.bat：停止容器的运行，并删除容器
* start.sh、start.bat：开始容器的运行（第一次启动请使用 setup.sh）
* stop.sh、stop.bat：停止容器的运行

### 部署流程

1. 运行 data-generate 下的 genTable_mongoDB10G.py 生成数据，数据将生成在 data 目录下
2. 进入 script 文件夹
3. 运行 setup.sh（Windows 运行 setup.bat），第一次加载 hdfs 的数据非常耗时，请耐心等待
4. 访问 localhost:8080 端口即可查看部署后的网页
5. 运行 start.sh 和 stop.sh（Windows 运行 start.bat 和 stop.bat）可以快速启动和停止容器，不删除数据
6. 使用完毕后运行shutdown.sh（Windows 运行 shutdown.bat）删除所有数据



## 系统使用

在宿主机浏览器访问 localhost:8080，即可进入 app 主页。

#### 主页

主页为文章热榜，榜单上的任意卡片都可点击，进而进入到相应的文章详情页面。

#### 文章详情页面

文章详情页面展示文章的详细信息、图片、视频、评论信息、相似文章列表以及目标推荐用户列表。

其中视频可点击播放，点击评论信息或目标推荐用户列表的卡片可进入相应的读者详情页面，点击相似文章列表的卡片可进入相应的文章详情页面。

#### 用户详情页面

用户详情页面展示了用户详细信息、阅读历史以及推荐文章列表，点击阅读历史或推荐文章列表可进入相应的文章详情页面。