package br.com.comicsencyclopedia.modules.comics.util;

import br.com.comicsencyclopedia.config.exception.ValidacaoException;
import br.com.comicsencyclopedia.modules.processorapi.dto.ComicsProcessorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static ComicsProcessorResponse toComics(String json) {
        try {
            var mapper = new ObjectMapper();
            return mapper.readValue(json, ComicsProcessorResponse.class);
        } catch (Exception ex) {
            throw new ValidacaoException("Error while trying to convert String json : "
                .concat(json)
                .concat(" - to Comics object. Error: ")
                .concat(ex.getMessage()));
        }
    }

    public static String toJson(Object object) {
        try {
            var mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new ValidacaoException("Error while trying to convert Java Object: "
                .concat(object.toString())
                .concat(" - to JSON String. Error: ")
                .concat(ex.getMessage()));
        }
    }
}
