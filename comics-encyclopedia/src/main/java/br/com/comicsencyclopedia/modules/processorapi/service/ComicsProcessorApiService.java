package br.com.comicsencyclopedia.modules.processorapi.service;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import br.com.comicsencyclopedia.modules.processorapi.client.ComicsProcessorApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ComicsProcessorApiService {

    @Autowired
    private ComicsProcessorApiClient client;

    public List<Comics> findComcisByName(String name) {
        try {
            var comics = client.findComcisByName(name);
            log.info("Successfully fetched data at Comics Processor API.");
            return comics;
        } catch (Exception ex) {
            log.info("Error while trying to consult Comic Hero API by name: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<Comics> findComcisById(String id) {
        try {
            var comics = Optional.of(client.findComcisById(id));
            log.info("Successfully fetched data at Comics Processor API.");
            return comics;
        } catch (Exception ex) {
            log.info("Error while trying to consult Comic Hero API by ID: {}", ex.getMessage());
            return Optional.empty();
        }
    }
}
