import express from "express";

import * as db from "./src/config/db/mongoDbConfig";
import * as envs from "./src/config/constants";
import * as kafka from "./src/config/kafka/kafkaConfig";
import * as consumer from "./src/modules/consumer/comicsConsumer";
import comics from "./src/modules/comics/routes/comicsRoutes";

const app = express();
const env = process.env;
const DEFAULT_PORT = 8081;
const PORT = env.PORT || DEFAULT_PORT;
const ONE_MINUTE = 60000;

console.info(`Actual env profile: ${env.NODE_ENV || envs.ENV_DEVELOPMENT}`);

waitForKafkaAndMongoDBIfEnvContainer();

async function waitForKafkaAndMongoDBIfEnvContainer() {
  let env = process.env;
  if (env.NODE_ENV === envs.ENV_CONTAINER) {
    console.info("Waiting for MongoDB and Apache Kafka containers to start...");
    setInterval(() => {
      startApplication();
    }, ONE_MINUTE);
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

app.listen(PORT, () => {
  console.info(`Application is active and running in port ${PORT}`);
});
