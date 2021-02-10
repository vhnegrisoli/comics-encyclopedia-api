package br.com.comicsencyclopedia.modules.processorapi.dto;

import br.com.comicsencyclopedia.modules.comics.enums.PublisherID;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterAppearance;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterBiography;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterConnections;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Image;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.PowerStats;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Work;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicsProcessorResponse {

    private String _id;

    private PublisherID publisherId;

    private String characterId;

    private String name;

    private String nameLower;

    private PowerStats powerstats;

    private CharacterBiography biography;

    private CharacterAppearance appearance;

    private Work work;

    private CharacterConnections connections;

    private Image image;

    private String __v;
}
