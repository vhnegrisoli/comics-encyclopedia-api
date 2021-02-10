import ComicsRespository from "../repository/comicsRepository";
import ComicsProducer from "../../producer/comicsProducer";

class ComicsService {
  async processComics(comicsMessage) {
    let comicsObject = JSON.parse(comicsMessage);
    let comics = await ComicsRespository.save(comicsObject);
    comics._id = null;
    comics.__v = null;
    await ComicsProducer.sendMessage(comics);
  }
}

export default new ComicsService();
