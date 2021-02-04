package br.com.comicsencyclopedia.modules.superheroapi.dto.comics;

import br.com.comicsencyclopedia.modules.superheroapi.dto.response.Connections;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CharacterConnections {

    private String groupAffiliation;

    private String relatives;

    public static CharacterConnections convertFrom(Connections connections) {
        var characterConnections = new CharacterConnections();
        BeanUtils.copyProperties(connections, characterConnections);
        return characterConnections;
    }
}
