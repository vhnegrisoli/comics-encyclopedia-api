package br.com.comicsencyclopedia.modules.comics.model;

import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterAppearance;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterBiography;
import br.com.comicsencyclopedia.modules.superheroapi.dto.comics.CharacterConnections;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.ComicsResult;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Image;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.PowerStats;
import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Work;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comics")
public class Comics {

    @Id
    private String id;

    private String characterId;

    private String name;

    private PowerStats powerstats;

    private CharacterBiography biography;

    private CharacterAppearance appearance;

    private Work work;

    private CharacterConnections connections;

    private Image image;

    public static Comics convertFrom(ComicsResult comicsResult) {
        var comics = new Comics();
        BeanUtils.copyProperties(comicsResult,comics);
        comics.setId(null);
        comics.setCharacterId(comicsResult.getId());
        comics.setAppearance(CharacterAppearance.convertFrom(comicsResult.getAppearance()));
        comics.setBiography(CharacterBiography.convertFrom(comicsResult.getBiography()));
        comics.setConnections(CharacterConnections.convertFrom(comicsResult.getConnections()));
        return comics;
    }
}
