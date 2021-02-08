import DcComics from "../model/DcComics";

class ComicsRepository {
  async save(comics) {
    let savedComics = await DcComics.create(comics);
    return savedComics;
  }

  async findByNameLowerContaining(name) {
    let comics = await DcComics.find({ nameLower: `/${name}/` });
  }

  async findByCharacterId(id) {
    let comics = await DcComics.findOne({ characterId: id });
    return comics;
  }
}

export default new ComicsRepository();
