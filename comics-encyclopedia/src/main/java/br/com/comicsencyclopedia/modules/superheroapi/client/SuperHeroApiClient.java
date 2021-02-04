package br.com.comicsencyclopedia.modules.superheroapi.client;

import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResponse;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "superHeroApiClient",
    url = "${app-config.services.super_hero_api.base_url}"
)
public interface SuperHeroApiClient {

    @GetMapping("/search/{name}")
    ComicsResponse findComcisByName(@PathVariable String name);

    @GetMapping("{id}")
    ComicsResult findComcisById(@PathVariable String id);
}
