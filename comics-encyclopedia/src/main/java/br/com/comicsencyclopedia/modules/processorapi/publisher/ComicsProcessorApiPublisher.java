package br.com.comicsencyclopedia.modules.processorapi.publisher;

import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ComicsProcessorApiPublisher {

    public void publishMessage(Object message) {
        var jsonStringMessage = JsonUtil.toJson(message);
        log.info("Publishing message {} at topic {}", message, "topic");
    }
}
