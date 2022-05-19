#configsvr1
#创建配置服务复制集
mongo --host 172.22.0.11 --port 27019
rs.initiate(
  {
    _id: "configsvr_rs",
    configsvr: true,
    members: [
      { _id : 0, host : "172.22.0.11:27019" },
      { _id : 1, host : "172.22.0.12:27019" },
      { _id : 2, host : "172.22.0.13:27019" }
    ]
  }
)
exit


#shardsvr1
#创建分片复制集
mongo --host 172.22.0.21 --port 27018
rs.initiate(
  {
    _id : "shardsvr_rs1",
    members: [
      { _id : 0, host : "172.22.0.21:27018" },
      { _id : 1, host : "172.22.0.22:27018" }
    ]
  }
)
exit


#shardsvr3
mongo --host 172.22.0.23 --port 27018
rs.initiate(
  {
    _id : "shardsvr_rs2",
    members: [
      { _id : 0, host : "172.22.0.23:27018" },
      { _id : 1, host : "172.22.0.24:27018" }
    ]
  }
)
exit


#mongos
mongo --host 172.22.0.2 --port 27017

# 添加 shard
sh.addShard("shardsvr_rs1/172.22.0.21:27018,172.22.0.22:27018")
sh.addShard("shardsvr_rs2/172.22.0.23:27018,172.22.0.24:27018")

# 将 shard 加入到 Zone 中
sh.addShardToZone("shardsvr_rs1", "DBMS1")
sh.addShardToZone("shardsvr_rs2", "DBMS2")
sh.addShardToZone("shardsvr_rs1", "DBMS1-2")
sh.addShardToZone("shardsvr_rs2", "DBMS1-2")

use ddbs

# 对 user 表进行分片
db.createCollection('user')
sh.updateZoneKeyRange("ddbs.user", { region: "Beijing", "uid" : MinKey}, { region: "Beijing", "uid" : MaxKey }, "DBMS1")
sh.updateZoneKeyRange("ddbs.user", { region: "Hong Kong", "uid" : MinKey}, { region: "Hong Kong", "uid" : MaxKey }, "DBMS2")
# 对 read 表进行分片
db.createCollection('read')
sh.updateZoneKeyRange("ddbs.read", { region: "Beijing"}, { region: "Beijing" }, "DBMS1")
sh.updateZoneKeyRange("ddbs.read", { region: "Hong Kong"}, { region: "Hong Kong" }, "DBMS2")
# 对 article 表进行分片
db.createCollection('article')
sh.updateZoneKeyRange("ddbs.article", { category: "science", "aid" : MinKey }, { category: "science", "aid" : MaxKey }, "DBMS1-2")
sh.updateZoneKeyRange("ddbs.article", { category: "technology", "aid" : MinKey }, { category: "technology", "aid" : MaxKey }, "DBMS2")
# 对 be-read 表进行分片
db.createCollection('beread')
sh.updateZoneKeyRange("ddbs.beread", { category: "science" }, { category: "science" }, "DBMS1-2")
sh.updateZoneKeyRange("ddbs.beread", { category: "technology" }, { category: "technology" }, "DBMS2")
# 对 rank 表进行分片
db.createCollection('rank')
sh.updateZoneKeyRange("ddbs.rank", { temporal_granularity: "daily" }, { temporal_granularity: "daily" }, "DBMS1")
sh.updateZoneKeyRange("ddbs.rank", { temporal_granularity: "weekly" }, { temporal_granularity: "weekly" }, "DBMS2")
sh.updateZoneKeyRange("ddbs.rank", { temporal_granularity: "monthly" }, { temporal_granularity: "monthly" }, "DBMS2")

# 启用分片
sh.enableSharding("ddbs")

# 对表进行 shard
sh.shardCollection("ddbs.user", {"region": 1, "uid":1 })
sh.shardCollection("ddbs.read", {"region": 1})
sh.shardCollection("ddbs.article", { "category": 1, "aid":1 })
sh.shardCollection("ddbs.beread", { "category": 1 })
sh.shardCollection("ddbs.rank", { "temporal_granularity": 1 })


#向mongos导入数据
mongoimport --host 172.22.0.2 --port 27017 -d ddbs -c user --file user.dat
mongoimport --host 172.22.0.2 --port 27017 -d ddbs -c article --file article.dat
mongoimport --host 172.22.0.2 --port 27017 -d ddbs -c read --file read.dat
mongoimport --host 172.22.0.2 --port 27017 -d ddbs -c beread --file beread.dat