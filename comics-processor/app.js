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
  const producer = kafka
    .getKafkaConnection()
    .producer({ allowAutoTopicCreation: true });
  let jsonMessage = {
    id: "Test",
    name: "Test",
    value: 156.88,
  };
  await producer.connect();
  await producer.send({
    topic: "dc_comics_response.topic",
    messages: [
      {
        value: `{"id":null,"publisherId":null,"characterId":"25","name":"Angel Dust","nameLower":"angel dust","powerstats":{"intelligence":"38","strength":"55","speed":"23","durability":"42","power":"17","combat":"30"},"biography":{"fullName":"Christina","alterEgos":"No alter egos found.","aliases":["Angel","Dusty"],"placeOfBirth":"-","firstAppearance":"Morlocks #1","publisher":"Marvel Comics","alignment":"good"},"appearance":{"gender":"Female","race":"Mutant","height":["5'5","165 cm"],"weight":["126 lb","57 kg"],"eyeColor":"Yellow","hairColor":"Black"},"work":{"occupation":"-","base":"Chicago, Illinois"},"connections":{"groupAffiliation":"-","relatives":"-"},"image":null}`,
      },
    ],
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
  console.info(`Application is active and running in port ${PORT}`);
});
