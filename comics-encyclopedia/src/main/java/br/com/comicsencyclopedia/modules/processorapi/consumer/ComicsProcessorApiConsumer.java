package br.com.comicsencyclopedia.modules.processorapi.consumer;

import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class ComicsProcessorApiConsumer {

    @KafkaListener(
        groupId = "${kafka.consumer.group-id}",
        topics = "${kafka.topic.dc-comics-response}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void receberComics(String message) {
        try {
            log.info("Recebido o objeto '{}'", message);
            var comics = JsonUtil.toComics(message);
            log.info("Objeto convertido: {}", comics.toString());
        } catch (Exception ex) {
            log.error("Erro ao receber dados do tópico.", ex);
        }
    }
}
