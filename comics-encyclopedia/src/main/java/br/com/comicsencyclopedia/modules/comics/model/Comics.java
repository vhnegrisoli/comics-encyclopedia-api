package br.com.comicsencyclopedia.modules.comics.model;

import br.com.comicsencyclopedia.modules.comics.enums.PublisherID;
import br.com.comicsencyclopedia.modules.processorapi.dto.ComicsProcessorResponse;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterAppearance;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterBiography;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterConnections;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Image;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.PowerStats;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

import static br.com.comicsencyclopedia.modules.comics.enums.PublisherID.NOT_INFORMED;
import static org.springframework.util.ObjectUtils.isEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comics_temp")
public class Comics {

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

    public static Comics convertFrom(ComicsResult comicsResult) {
        return Comics
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
            .image(comicsResult.getImage())
            .publisherId(getCharacterPublisher(!isEmpty(comicsResult.getBiography())
                ? comicsResult.getBiography().getPublisher() : null))
            .build();
    }

    public static Comics convertFrom(ComicsProcessorResponse response) {
        return Comics
            .builder()
            .id(null)
            .characterId(response.getCharacterId())
            .name(response.getName())
            .nameLower(response.getName().toLowerCase())
            .powerstats(response.getPowerstats())
            .biography(response.getBiography())
            .appearance(response.getAppearance())
            .work(response.getWork())
            .connections(response.getConnections())
            .image(response.getImage())
            .publisherId(response.getPublisherId())
            .build();
    }

    private static PublisherID getCharacterPublisher(String publisherName) {
        return Arrays
            .stream(PublisherID.values())
            .filter(publisher -> !isEmpty(publisherName)
                && publisher.getPublisherName().equals(publisherName))
            .findFirst()
            .orElse(NOT_INFORMED);
    }
}
