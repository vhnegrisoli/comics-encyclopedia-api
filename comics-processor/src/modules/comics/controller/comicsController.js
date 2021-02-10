import ComicsService from "../service/comicsService";

class ComicsController {
  async findByCharacterId(req, res) {
    let comics = await ComicsService.findByCharacterId(req);
    return res.status(comics.status).json(comics.data);
  }

  async findByNameLowerContaining(req, res) {
    let comics = await ComicsService.findByNameLowerContaining(req);
    return res.status(comics.status).json(comics.data);
  }
}

export default new ComicsController();
