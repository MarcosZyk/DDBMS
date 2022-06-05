# DDBMS 部署方案

## 安装与配置

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

zyk