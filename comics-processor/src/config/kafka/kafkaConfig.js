import { Kafka } from "kafkajs";
import * as secrets from "../secrets/secrets";
import * as topics from "./topics";

let kafka = null;

export function connect() {
  console.info(`Kafka Connection: ${secrets.KAFKA_CONNECTION}`);
  try {
    const config = new Kafka({
      clientId: "comics-encyclopedia",
      brokers: [secrets.KAFKA_CONNECTION],
    });
    createTopics(config);
    kafka = config;
  } catch (error) {
    console.info(
      `Error while trying to connect to Apache Kafka. Error: ${error.message}`
    );
  }
}

function createTopics(config) {
  const admin = config.admin();
  admin.connect();
  console.info("Application successfully conected to Apache Kafka.");
  admin.createTopics({
    topics: [
      { topic: topics.DC_COMICS_REQUEST_TOPIC },
      { topic: topics.DC_COMICS_RESPONSE_TOPIC },
      { topic: topics.MARVEL_COMICS_REQUEST_TOPIC },
      { topic: topics.MARVEL_COMICS_RESPONSE_TOPIC },
      { topic: topics.NOT_INFORMED_PUBLISHER_REQUEST_TOPIC },
      { topic: topics.NOT_INFORMED_PUBLISHER__RESPONSE_TOPIC },
    ],
  });
  admin.disconnect();
}

export function getKafkaConnection() {
  return kafka;
}
