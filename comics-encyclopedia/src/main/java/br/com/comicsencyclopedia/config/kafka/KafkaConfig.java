package br.com.comicsencyclopedia.config.kafka;

import br.com.comicsencyclopedia.modules.processorapi.consumer.ComicsProcessorApiConsumer;
import br.com.comicsencyclopedia.modules.processorapi.publisher.ComicsProcessorApiPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConsumerConfigs() {
        log.info("Default Kafka Producer Bootstrap Server: {}", bootstrapServers);
        var propriedades = new HashMap<String, Object>();

        propriedades.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        propriedades.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propriedades.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        propriedades.put(ConsumerConfig.GROUP_ID_CONFIG, "comics_encyclopedia_group");
        propriedades.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propriedades.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return propriedades;
    }

    @Bean
    public ProducerFactory<String, String> producerConsumerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConsumerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerConsumerFactory());
    }

    @Bean
    public ComicsProcessorApiPublisher sender() {
        return new ComicsProcessorApiPublisher();
    }

    @Bean
    public ComicsProcessorApiConsumer listener() {
        return new ComicsProcessorApiConsumer();
    }
}
