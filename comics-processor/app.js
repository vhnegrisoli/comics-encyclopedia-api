import express from "express";

import * as db from "./src/config/db/mongoDbConfig";
import * as kafka from "./src/config/kafka/kafkaConfig";
import * as consumer from "./src/modules/consumer/comicsConsumer";
import comics from "./src/modules/comics/routes/comicsRoutes";

const app = express();
const env = process.env;

console.info(`Actual env profile: ${env.NODE_ENV || "dev"}`);

waitForKafkaAndMongoDB();

async function waitForKafkaAndMongoDB() {
  let env = process.env;
  const TWENTY_SECONDS = 30000;
  if (env.NODE_ENV === "container") {
    console.info("Waiting for MongoDB and Apache Kafka containers to start...");
    setInterval(() => {
      startApplication();
    }, TWENTY_SECONDS);
  } else {
    startApplication();
  }
}

function startApplication() {
  db.connect();
  kafka.connect();
  consumer.consumeMessages();
}

app.use(comics);
const PORT = env.PORT || 8081;

app.listen(PORT, () => {
  console.info(`Application is active and running in port ${PORT}`);
});
