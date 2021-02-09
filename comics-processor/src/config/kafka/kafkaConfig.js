import { Kafka } from "kafkajs";
import * as secrets from "../secrets/secrets";

let kafka = null;

export function connect() {
  try {
    const config = new Kafka({
      clientId: "comics-encyclopedia",
      brokers: [secrets.KAFKA_CONNECTION],
    });
    kafka = config;
  } catch (error) {
    console.log("Error while trying to connect to Apache Kafka.");
    console.log(error);
  }
}

export function getKafkaConnection() {
  return kafka;
}
