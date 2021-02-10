import * as kafkaConfig from "../../config/kafka/kafkaConfig";
import * as secrets from "../../config/secrets/secrets";
import * as topics from "../../config/kafka/topics";
import ComicsService from "../comics/service/comicsService";

export async function consumeMessages() {
  try {
    const consumer = getConsumer();
    await subscribeTopics(consumer);
    await consumer.connect();
    await consumer.run({
      eachMessage: async ({ topic, partition, message }) => {
        processMessage(message.value.toString());
      },
    });
  } catch (error) {
    console.error(
      `Error while trying to connect to Kafka for consuming messages. Error: ${error.message}`
    );
  }
}

function getConsumer() {
  return kafkaConfig.getKafkaConnection().consumer({
    groupId: secrets.KAFKA_GROUP_ID,
    allowAutoTopicCreation: true,
  });
}

async function subscribeTopics(consumer) {
  await consumer.subscribe({
    topic: topics.DC_COMICS_REQUEST_TOPIC,
    fromBeginning: true,
  });
  await consumer.subscribe({
    topic: topics.MARVEL_COMICS_REQUEST_TOPIC,
    fromBeginning: true,
  });
  await consumer.subscribe({
    topic: topics.NOT_INFORMED_PUBLISHER_REQUEST_TOPIC,
    fromBeginning: true,
  });
}

async function processMessage(message) {
  console.info(`Recieved message from Kafka: ${message}`);
  try {
    await ComicsService.processComics(message);
  } catch (error) {
    console.error(`Error while trying to parse message: ${error.message}`);
    console.error(error);
  }
}
