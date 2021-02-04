package br.com.comicsencyclopedia.modules.superheroapi.dto.comics;

import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Biography;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class CharacterBiography {

    private String fullName;

    private String alterEgos;

    private List<String> aliases;

    private String placeOfBirth;

    private String firstAppearance;

    private String publisher;

    private String alignment;

    public static CharacterBiography convertFrom(Biography biography) {
        var characterBiography = new CharacterBiography();
        BeanUtils.copyProperties(biography, characterBiography);
        return characterBiography;
    }
}
