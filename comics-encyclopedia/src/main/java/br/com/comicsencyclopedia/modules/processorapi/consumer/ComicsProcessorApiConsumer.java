package br.com.comicsencyclopedia.modules.processorapi.consumer;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import br.com.comicsencyclopedia.modules.comics.service.ComicsService;
import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class ComicsProcessorApiConsumer {

    @Autowired
    private ComicsService comicsService;

    @KafkaListener(
        groupId = "${kafka.consumer.group-id}",
        topics = "${kafka.topic.dc-comics-response}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeDcComicsResponse(String message) {
        try {
            log.info("Message recieved from consumer: '{}'", message);
            var comicsResponse = JsonUtil.toComics(message);
            log.info("Publisher: {}", comicsResponse.getPublisherId());
            comicsService.saveComics(Comics.convertFrom(comicsResponse));
        } catch (Exception ex) {
            log.error("Error when recieving object from Kafka.", ex);
        }
    }

    @KafkaListener(
        groupId = "${kafka.consumer.group-id}",
        topics = "${kafka.topic.marvel-comics-response}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeMarvelComicsResponse(String message) {
        try {
            log.info("Message recieved from consumer: '{}'", message);
            var comicsResponse = JsonUtil.toComics(message);
            log.info("Publisher: {}", comicsResponse.getPublisherId());
            comicsService.saveComics(Comics.convertFrom(comicsResponse));
        } catch (Exception ex) {
            log.error("Error when recieving object from Kafka.", ex);
        }
    }

    @KafkaListener(
        groupId = "${kafka.consumer.group-id}",
        topics = "${kafka.topic.not-informed-publisher-response}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeNotInformedPublisherResponse(String message) {
        try {
            log.info("Message recieved from consumer: '{}'", message);
            var comicsResponse = JsonUtil.toComics(message);
            log.info("Publisher: {}", comicsResponse.getPublisherId());
            comicsService.saveComics(Comics.convertFrom(comicsResponse));
        } catch (Exception ex) {
            log.error("Error when recieving object from Kafka.", ex);
        }
    }
}
