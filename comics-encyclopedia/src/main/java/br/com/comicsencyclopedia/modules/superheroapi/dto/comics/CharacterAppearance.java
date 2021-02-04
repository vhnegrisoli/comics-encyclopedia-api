package br.com.comicsencyclopedia.modules.superheroapi.dto.comics;

import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Appearance;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class CharacterAppearance {

    private String gender;

    private String race;

    private List<String> height;

    private List<String> weight;

    private String eyeColor;

    private String hairColor;

    public static CharacterAppearance convertFrom(Appearance appearance) {
        var characterAppearance = new CharacterAppearance();
        BeanUtils.copyProperties(appearance, characterAppearance);
        return characterAppearance;
    }
}
