package br.com.comicsencyclopedia.modules.comics.repository;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComicsRepository extends MongoRepository<Comics, String> {

    List<Comics> findByNameLikeIgnoreCase(String name);

    Optional<Comics> findByCharacterId(String id);
}
