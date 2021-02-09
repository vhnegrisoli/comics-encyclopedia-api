import { Kafka } from "kafkajs";
import * as secrets from "../secrets/secrets";
import * as topics from "./topics";

let kafka = null;

export function connect() {
  try {
    const config = new Kafka({
      clientId: "comics-encyclopedia",
      brokers: [secrets.KAFKA_CONNECTION],
    });
    createTopics(config);
    kafka = config;
  } catch (error) {
    console.log("Error while trying to connect to Apache Kafka.");
    console.log(error);
  }
}

function createTopics(config) {
  const admin = config.admin();
  admin.connect();
  console.log("Application successfully conected to Apache Kafka.");
  admin.createTopics({
    topics: [
      { topic: topics.DC_COMICS_REQUEST_TOPIC },
      { topic: topics.DC_COMICS_RESPONSE_TOPIC },
      { topic: topics.MARVEL_COMICS_REQUEST_TOPIC },
      { topic: topics.MARVEL_COMICS_RESPONSE_TOPIC },
    ],
  });
  admin.disconnect();
}

export function getKafkaConnection() {
  return kafka;
}
