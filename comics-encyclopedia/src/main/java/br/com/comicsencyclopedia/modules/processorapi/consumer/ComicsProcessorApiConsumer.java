package br.com.comicsencyclopedia.modules.processorapi.consumer;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import br.com.comicsencyclopedia.modules.comics.service.ComicsService;
import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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

    private String excludeMongoDbId(String message) {
        var idField = "\"_id\":null,";
        var versionField = ",\"__v\":null";
        if (message.contains(idField)) {
            message = message.replace(idField, Strings.EMPTY);
        }
        if (message.contains(versionField)) {
            message = message.replace(versionField, Strings.EMPTY);
        }
        return message;
    }
}
