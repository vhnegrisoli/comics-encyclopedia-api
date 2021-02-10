package br.com.comicsencyclopedia.modules.comics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PublisherID {

    DC("DC Comics"),
    MARVEL("Marvel Comics"),
    NOT_INFORMED("Not Informed");

    @Getter
    private final String publisherName;
}
