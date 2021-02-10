package br.com.comicsencyclopedia.modules.processorapi.publisher;

import br.com.comicsencyclopedia.modules.comics.enums.PublisherID;
import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import static br.com.comicsencyclopedia.modules.comics.enums.PublisherID.DC;
import static br.com.comicsencyclopedia.modules.comics.enums.PublisherID.MARVEL;
import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class ComicsProcessorApiPublisher {

    @Value("${kafka.topic.dc-comics-request}")
    private String dcComicsRequestTopic;

    @Value("${kafka.topic.marvel-comics-request}")
    private String marvelComicsRequestTopic;

    @Value("${kafka.topic.not-informed-publisher-request}")
    private String notInformedPublisherRequest;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessage(Object message, PublisherID publisherID) {
        try {
            var jsonStringMessage = JsonUtil.toJson(message);
            var topic = defineTopicBasedOnPublisherId(publisherID);
            log.info("Publishing message {} at topic {}", jsonStringMessage, topic);
            kafkaTemplate.send(topic, jsonStringMessage);
            log.info("Message sent with success!");
        } catch (Exception ex) {
            log.error("Error while trying to send message to Kafka.", ex);
        }
    }

    private String defineTopicBasedOnPublisherId(PublisherID publisherID) {
        return DC.equals(publisherID)
            ? dcComicsRequestTopic
            : MARVEL.equals(publisherID)
            ? marvelComicsRequestTopic
            : notInformedPublisherRequest;
    }
}
