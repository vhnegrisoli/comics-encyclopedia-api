import ComicsRespository from "../repository/comicsRepository";
import ComicsProducer from "../../producer/comicsProducer";
import * as httpStatus from "../../../config/constants";
import ComicsException from "../exception/comicsException";

class ComicsService {
  async findByCharacterId(req) {
    try {
      const { characterId } = req.params;
      this.validateExistingCharacterId(characterId);
      let comics = await ComicsRespository.findByCharacterId(characterId);
      if (!comics) {
        throw new ComicsException(
          httpStatus.BAD_REQUEST,
          `No character was found by id ${characterId}`
        );
      }
      return { status: httpStatus.SUCCESS, data: comics };
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        data: error.message,
      };
    }
  }

  async findByNameLowerContaining(req) {
    try {
      let { name } = req.params;
      this.validateExistingName(name);
      name = name.toLowerCase();
      let comics = await ComicsRespository.findByNameLowerContaining(name);
      if (!comics) {
        throw new ComicsException(
          httpStatus.BAD_REQUEST,
          `No characters was found by searching for "${characterId}"`
        );
      }
      return { status: httpStatus.SUCCESS, data: comics };
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        data: error.message,
      };
    }
  }

  validateExistingCharacterId(characterId) {
    if (!characterId) {
      throw new ComicsException(
        httpStatus.BAD_REQUEST,
        "The character ID cannot be empty."
      );
    }
  }

  validateExistingName(name) {
    if (!name) {
      throw new ComicsException(
        httpStatus.BAD_REQUEST,
        "The name cannot be empty."
      );
    }
  }

  async processComics(comicsMessage) {
    let comicsObject = JSON.parse(comicsMessage);
    let comics = await ComicsRespository.save(comicsObject);
    await ComicsProducer.sendMessage(comics);
  }
}

export default new ComicsService();
