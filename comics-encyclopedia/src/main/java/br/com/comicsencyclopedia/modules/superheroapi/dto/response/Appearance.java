package br.com.comicsencyclopedia.modules.superheroapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Appearance {

    private String gender;

    private String race;

    private List<String> height;

    private List<String> weight;

    @JsonProperty("eye-color")
    private String eyeColor;

    @JsonProperty("hair-color")
    private String hairColor;

}
