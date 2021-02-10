import Comics from "../model/Comics";

class ComicsRepository {
  async save(comics) {
    let savedComics = await Comics.create(comics);
    return savedComics;
  }

  async findByNameLowerContaining(name) {
    console.info(`Trying to search for ${name}`);
    let comics = await Comics.find({ nameLower: { $regex: `.*${name}.*` } });
    return comics;
  }

  async findByCharacterId(id) {
    let comics = await Comics.findOne({ characterId: id });
    return comics;
  }
}

export default new ComicsRepository();
