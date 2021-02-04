package br.com.comicsencyclopedia.modules.superheroapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Connections {

    @JsonProperty("group-affiliation")
    private String groupAffiliation;

    private String relatives;
}
