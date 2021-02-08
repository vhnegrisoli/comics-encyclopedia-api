package br.com.comicsencyclopedia.modules.comics.service;

import br.com.comicsencyclopedia.config.exception.ValidacaoException;
import br.com.comicsencyclopedia.modules.comics.model.Comics;
import br.com.comicsencyclopedia.modules.comics.repository.ComicsRepository;
import br.com.comicsencyclopedia.modules.comics.util.JsonUtil;
import br.com.comicsencyclopedia.modules.processorapi.publisher.ComicsProcessorApiPublisher;
import br.com.comicsencyclopedia.modules.processorapi.service.ComicsProcessorApiService;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResponse;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import br.com.comicsencyclopedia.modules.superheroapi.service.SuperHeroApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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
            publisher.publishMessage(superHeroApiComics);
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
            var response = superHeroApiService.findComcisById(String.valueOf(id));
            var comics = Comics.convertFrom(response.get());
            return repository.save(comics);
        });
    }

    public String teste(String id) {
        var existingComics = repository.findByCharacterId(id);
        return JsonUtil.toJson(existingComics.get());
    }
}