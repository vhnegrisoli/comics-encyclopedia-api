package br.com.comicsencyclopedia.modules.comics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PublisherID {

    DC("DC Comics"),
    MARVEL("Marvel Comics");

    @Getter
    private final String publisherName;
}
