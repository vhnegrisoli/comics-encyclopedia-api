package br.com.comicsencyclopedia.modules.comics.controller;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import br.com.comicsencyclopedia.modules.comics.service.ComicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comics/character")
public class ComicsController {

    private final ComicsService service;

    @GetMapping("name/{name}")
    public List<Comics> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("id/{id}")
    public Comics findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("teste/{id}")
    public String teste(@PathVariable String id) {
        return service.teste(id);
    }
}
