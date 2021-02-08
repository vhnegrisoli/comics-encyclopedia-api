package br.com.comicsencyclopedia.modules.comics.model;

import br.com.comicsencyclopedia.modules.comics.enums.PublisherID;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterAppearance;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterBiography;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterConnections;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Image;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.PowerStats;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Work;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@Document(collection = "dc_comics_temporary")
public class DcComics {

    @Id
    private String id;

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

    public static DcComics convertFrom(ComicsResult comicsResult) {
        return DcComics
            .builder()
            .id(null)
            .characterId(comicsResult.getId())
            .name(comicsResult.getName())
            .nameLower(comicsResult.getName().toLowerCase())
            .powerstats(comicsResult.getPowerstats())
            .biography(CharacterBiography.convertFrom(comicsResult.getBiography()))
            .appearance(CharacterAppearance.convertFrom(comicsResult.getAppearance()))
            .work(comicsResult.getWork())
            .connections(CharacterConnections.convertFrom(comicsResult.getConnections()))
            .build();
    }
}
