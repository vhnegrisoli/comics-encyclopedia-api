import Comics from "../model/Comics";

class ComicsRepository {
  async save(comics) {
    let savedComics = await Comics.create(comics);
    return savedComics;
  }

  async findByNameLowerContaining(name) {
    let comics = await Comics.find({ nameLower: `/${name}/` });
    return comics;
  }

  async findByCharacterId(id) {
    let comics = await Comics.findOne({ characterId: id });
    return comics;
  }
}

export default new ComicsRepository();
