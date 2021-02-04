package br.com.comicsencyclopedia.modules.superheroapi.service;

import br.com.comicsencyclopedia.modules.superheroapi.client.SuperHeroApiClient;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResponse;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SuperHeroApiService {

    @Autowired
    private SuperHeroApiClient client;

    public Optional<ComicsResponse> findComcisByName(String name) {
        try {
            return Optional.of(client.findComcisByName(name));
        } catch (Exception ex) {
            log.info("Error while trying to consult Comic Hero API by name: {}", ex.getMessage());
            return Optional.empty();
        }
    }

    public Optional<ComicsResult> findComcisById(String id) {
        try {
            return Optional.of(client.findComcisById(id));
        } catch (Exception ex) {
            log.info("Error while trying to consult Comic Hero API by ID: {}", ex.getMessage());
            return Optional.empty();
        }
    }
}