import express from "express";

import * as db from "./src/config/db/mongoDbConfig";
import * as kafka from "./src/config/kafka/kafkaConfig";
import * as consumer from "./src/modules/consumer/comicsConsumer";

const app = express();
const env = process.env;

db.connect();
kafka.connect();
consumer.consumeMessages();

app.get("/send", async (req, res) => {
  const producer = kafka.getKafkaConnection().producer();

  await producer.connect();
  await producer.send({
    topic: "dc_comics_request.topic",
    messages: [{ value: "Hello KafkaJS user!" }],
  });
  await producer.disconnect();

  return res.json({
    message: "OK",
  });
});

app.get("/", (req, res) => {
  return res.json({
    message: "OK",
  });
});

const PORT = env.PORT || 8081;

app.listen(PORT, () => {
  console.log(`Application is active and running in port ${PORT}`);
});
