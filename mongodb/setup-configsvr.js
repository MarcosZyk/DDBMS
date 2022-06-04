rs.initiate(
  {
    _id: "configsvr_rs",
    configsvr: true,
    members: [
      { _id: 0, host: "172.20.0.3:27019" },
      { _id: 1, host: "172.20.0.4:27019" },
      { _id: 2, host: "172.20.0.5:27019" }
    ]
  }
);