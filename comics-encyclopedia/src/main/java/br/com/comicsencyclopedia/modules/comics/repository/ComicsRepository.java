package br.com.comicsencyclopedia.modules.comics.repository;

import br.com.comicsencyclopedia.modules.comics.model.Comics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ComicsRepository extends MongoRepository<Comics, String> {

    List<Comics> findByNameLowerLike(String name);

    Optional<Comics> findByCharacterId(String id);
}
