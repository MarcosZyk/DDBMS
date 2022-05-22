// 添加 shard
sh.addShard("shardsvr_rs1/shardsvr1:27018,shardsvr2:27018");
sh.addShard("shardsvr_rs2/shardsvr3:27018,shardsvr4:27018");

// 将 shard 加入到 Zone 中
sh.addShardToZone("shardsvr_rs1", "DBMS1");
sh.addShardToZone("shardsvr_rs2", "DBMS2");
sh.addShardToZone("shardsvr_rs1", "DBMS1-2");
sh.addShardToZone("shardsvr_rs2", "DBMS1-2");

db = db.getSiblingDB('ddbs')

// 对 user 表进行分片
db.createCollection('user');
sh.updateZoneKeyRange("ddbs.user", { region: "Beijing", "uid": MinKey }, { region: "Beijing", "uid": MaxKey }, "DBMS1");
sh.updateZoneKeyRange("ddbs.user", { region: "Hong Kong", "uid": MinKey }, { region: "Hong Kong", "uid": MaxKey }, "DBMS2");
// 对 read 表进行分片
db.createCollection('read');
sh.updateZoneKeyRange("ddbs.read", { region: "Beijing", "id": MinKey }, { region: "Beijing", "id": MaxKey }, "DBMS1");
sh.updateZoneKeyRange("ddbs.read", { region: "Hong Kong", "id": MinKey }, { region: "Hong Kong", "id": MaxKey }, "DBMS2");
// 对 article 表进行分片
db.createCollection('article');
sh.updateZoneKeyRange("ddbs.article", { category: "science", "aid": MinKey }, { category: "science", "aid": MaxKey }, "DBMS1-2");
sh.updateZoneKeyRange("ddbs.article", { category: "technology", "aid": MinKey }, { category: "technology", "aid": MaxKey }, "DBMS2");
// 对 be - read 表进行分片
db.createCollection('beread');
sh.updateZoneKeyRange("ddbs.beread", { category: "science", "id": MinKey }, { category: "science", "id": MaxKey }, "DBMS1-2");
sh.updateZoneKeyRange("ddbs.beread", { category: "technology", "id": MinKey }, { category: "technology", "id": MaxKey }, "DBMS2");
// 对 rank 表进行分片
db.createCollection('rank');
sh.updateZoneKeyRange("ddbs.rank", { temporal_granularity: "daily", "id": MinKey }, { temporal_granularity: "daily", "id": MaxKey }, "DBMS1");
sh.updateZoneKeyRange("ddbs.rank", { temporal_granularity: "weekly", "id": MinKey }, { temporal_granularity: "weekly", "id": MaxKey }, "DBMS2");
sh.updateZoneKeyRange("ddbs.rank", { temporal_granularity: "monthly", "id": MinKey }, { temporal_granularity: "monthly", "id": MaxKey }, "DBMS2");

// 启用分片
sh.enableSharding("ddbs");

// 对表进行 shard
sh.shardCollection("ddbs.user", { "region": 1, "uid": 1 });
sh.shardCollection("ddbs.read", { "region": 1, "id": 1 });
sh.shardCollection("ddbs.article", { "category": 1, "aid": 1 });
sh.shardCollection("ddbs.beread", { "category": 1, "id": 1 });
sh.shardCollection("ddbs.rank", { "temporal_granularity": 1, "id": 1 });