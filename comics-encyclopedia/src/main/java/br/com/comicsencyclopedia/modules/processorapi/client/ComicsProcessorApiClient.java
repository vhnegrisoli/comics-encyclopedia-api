package br.com.comicsencyclopedia.modules.processorapi.client;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
    name = "comicsProcessorApiClient",
    url = "${app-config.services.comics_processing.base_url}"
)
public interface ComicsProcessorApiClient {

    @GetMapping("name/{name}")
    List<Comics> findComcisByName(@PathVariable String name);

    @GetMapping("{id}")
    Comics findComcisById(@PathVariable String id);
}