import * as kafka from "../../config/kafka/kafkaConfig";
import * as topics from "../../config/kafka/topics";

class ComicsProducer {
  async sendMessage(comics) {
    try {
      const producer = kafka
        .getKafkaConnection()
        .producer({ allowAutoTopicCreation: true });
      let topic = this.defineTopic(comics);
      let jsonStringMessage = JSON.stringify(comics);
      console.info(`Sending message: ${jsonStringMessage}`);
      await producer.connect();
      await producer.send({
        topic: topic,
        messages: [
          {
            value: jsonStringMessage,
          },
        ],
      });
      await producer.disconnect();
      console.info("Message sent successfully.");
    } catch (error) {
      console.error(
        `Error while trying to send message to Kafka. Error: {error.message}`
      );
    }
  }

  defineTopic(comics) {
    return comics.publisherID && comics.publisherID === "DC"
      ? topics.DC_COMICS_RESPONSE_TOPIC
      : comics.publisherID === "MARVEL"
      ? topics.MARVEL_COMICS_RESPONSE_TOPIC
      : topics.NOT_INFORMED_PUBLISHER__RESPONSE_TOPIC;
  }
}
export default new ComicsProducer();
