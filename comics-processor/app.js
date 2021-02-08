import express from "express";

import * as db from "./src/config/db/mongoDbConfig";
import * as kafka from "./src/config/kafka/kafkaConfig";

const app = express();
const env = process.env;

db.connect();
kafka.connect();

app.get("/", (req, res) => {
  return res.json({
    message: "OK",
  });
});

const PORT = env.PORT || 8081;

app.listen(PORT, () => {
  console.log(`Application is active and running in port ${PORT}`);
});
