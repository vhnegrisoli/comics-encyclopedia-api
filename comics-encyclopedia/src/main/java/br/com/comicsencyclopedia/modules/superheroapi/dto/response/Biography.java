package br.com.comicsencyclopedia.modules.superheroapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Biography {

    @JsonProperty("full-name")
    private String fullName;

    @JsonProperty("alter-egos")
    private String alterEgos;

    private List<String> aliases;

    @JsonProperty("place-of-birth")
    private String placeOfBirth;

    @JsonProperty("first-appearance")
    private String firstAppearance;

    private String publisher;

    private String alignment;
}
