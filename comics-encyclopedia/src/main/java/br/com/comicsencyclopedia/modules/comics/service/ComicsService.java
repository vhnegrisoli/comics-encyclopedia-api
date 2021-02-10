package br.com.comicsencyclopedia.modules.comics.service;

import br.com.comicsencyclopedia.config.exception.ValidacaoException;
import br.com.comicsencyclopedia.modules.comics.model.Comics;
import br.com.comicsencyclopedia.modules.comics.repository.ComicsRepository;
import br.com.comicsencyclopedia.modules.processorapi.publisher.ComicsProcessorApiPublisher;
import br.com.comicsencyclopedia.modules.processorapi.service.ComicsProcessorApiService;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResponse;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import br.com.comicsencyclopedia.modules.superheroapi.service.SuperHeroApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ComicsService {

    @Autowired
    private ComicsRepository repository;

    @Autowired
    private SuperHeroApiService superHeroApiService;

    @Autowired
    private ComicsProcessorApiService comicsProcessorApiService;

    @Autowired
    private ComicsProcessorApiPublisher publisher;

    public List<Comics> findByName(String name) {
        name = name.toLowerCase();
        var comics = repository.findByNameLowerLike(name);
        if (!isEmpty(comics)) {
            return comics;
        }
        var comicsProcessorApi = comicsProcessorApiService.findComcisByName(name);
        if (!isEmpty(comicsProcessorApi)) {
            return comicsProcessorApi;
        }
        var superHeroApiComics = getComicsFromSuperHeroApi(name);
        if (!isEmpty(superHeroApiComics)) {
            superHeroApiComics.forEach(character -> publisher
                .publishMessage(character, character.getPublisherId()));
        }
        return superHeroApiComics;
    }

    private List<Comics> getComicsFromSuperHeroApi(String name) {
        return superHeroApiService
            .findComcisByName(name)
            .map(ComicsResponse::getResults)
            .orElseGet(Collections::emptyList)
            .stream()
            .map(Comics::convertFrom)
            .collect(Collectors.toList());
    }

    public Comics findById(String id) {
        var existingComics = repository.findByCharacterId(id);
        return existingComics.orElseGet(() -> {
            var response = superHeroApiService.findComcisById(id);
            var comicsResult = response
                .orElseThrow(() -> new ValidacaoException("Item not found by ID ".concat(id)));
            validateExistingData(comicsResult);
            var comics = Comics.convertFrom(comicsResult);
            publisher.publishMessage(comics, comics.getPublisherId());
            return comics;
        });
    }

    private void validateExistingData(ComicsResult comicsResult) {
        if (isEmpty(comicsResult.getId())
            || isEmpty(comicsResult.getName())) {
            throw new ValidacaoException("Item has no data to show.");
        }
    }

    public void deleteDataIfExists() {
        var existingComics = repository.findAll();
        if (!isEmpty(existingComics)) {
            repository.deleteAll();
        }
    }

    public void saveComics(Comics comics) {
        repository.save(comics);
    }
}