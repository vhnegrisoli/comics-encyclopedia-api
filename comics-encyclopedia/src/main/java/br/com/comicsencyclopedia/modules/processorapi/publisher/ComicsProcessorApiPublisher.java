package br.com.comicsencyclopedia.modules.processorapi.publisher;

import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class ComicsProcessorApiPublisher {

    @Value("${kafka.topic.dc-comics-request}")
    private String dcComicsRequestTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessage(Object message) {
        try {
            var jsonStringMessage = JsonUtil.toJson(message);
            log.info("Publishing message {} at topic {}", jsonStringMessage, dcComicsRequestTopic);
            kafkaTemplate.send(dcComicsRequestTopic, jsonStringMessage);
            log.info("Enviado com sucesso!");
        } catch (Exception ex) {
            log.error("Erro ao enviar para o o Kafka.", ex);
        }
    }
}
