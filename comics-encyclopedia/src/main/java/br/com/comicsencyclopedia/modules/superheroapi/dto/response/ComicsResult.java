package br.com.comicsencyclopedia.modules.superheroapi.dto.response;

import lombok.Data;

@Data
public class ComicsResult {

    private String id;

    private String name;

    private PowerStats powerstats;

    private Biography biography;

    private Appearance appearance;

    private Work work;

    private Connections connections;

    private Image image;
}
