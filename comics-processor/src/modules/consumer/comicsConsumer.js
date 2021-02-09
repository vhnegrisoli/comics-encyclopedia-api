import * as kafkaConfig from "../../config/kafka/kafkaConfig";
import * as secrets from "../../config/secrets/secrets";
import * as topics from "../../config/kafka/topics";

export async function consumeMessages() {
  try {
    const consumer = getConsumer();
    await subscribeTopics(consumer);
    await consumer.connect();
    await consumer.run({
      eachMessage: async ({ topic, partition, message }) => {
        console.log(topic);
        console.log(partition);
        processMessage(message.value.toString());
      },
    });
  } catch (error) {
    console.log(
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
}

function processMessage(message) {
  console.log(message);
  try {
    let jsonMessage = JSON.parse(message);
    console.log(jsonMessage);
  } catch (error) {
    console.log(
      `Error while trying to parse message: ${message} - Error: ${error.message}`
    );
  }
}
