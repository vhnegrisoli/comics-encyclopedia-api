package br.com.comicsencyclopedia.modules.superheroapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ComicsResponse {

    private String response;

    @JsonProperty("results-for")
    private String resultsFor;

    private List<ComicsResult> results;
}
