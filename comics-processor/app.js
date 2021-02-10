import express from "express";

import * as db from "./src/config/db/mongoDbConfig";
import * as kafka from "./src/config/kafka/kafkaConfig";
import * as consumer from "./src/modules/consumer/comicsConsumer";
import comics from "./src/modules/comics/routes/comicsRoutes";

const app = express();
const env = process.env;

db.connect();
kafka.connect();
consumer.consumeMessages();

app.use(comics);
const PORT = env.PORT || 8081;

app.listen(PORT, () => {
  console.info(`Application is active and running in port ${PORT}`);
});
