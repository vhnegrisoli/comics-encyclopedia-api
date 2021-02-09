package br.com.comicsencyclopedia.modules.comics.util;

import br.com.comicsencyclopedia.config.exception.ValidacaoException;
import br.com.comicsencyclopedia.modules.comics.model.Comics;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static Comics toComics(String json) {
        try {
            var mapper = new ObjectMapper();
            return mapper.readValue(json, Comics.class);
        } catch (Exception ex) {
            throw new ValidacaoException("Erro ao tentar gerar objeto Comics a partir do JSON em String: "
                .concat(json)
                .concat(" - erro: ")
                .concat(ex.getMessage()));
        }
    }

    public static String toJson(Object object) {
        try {
            var mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new ValidacaoException("Erro ao tentar gerar JSON String do objeto: "
                .concat(object.toString())
                .concat(" - erro: ")
                .concat(ex.getMessage()));
        }
    }
}
