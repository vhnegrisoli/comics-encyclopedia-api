import { Router } from "express";
import ComicsController from "../controller/comicsController";

const router = new Router();

router.get(
  "/api/comics/characterId/:characterId",
  ComicsController.findByCharacterId
);
router.get(
  "/api/comics/name/:name",
  ComicsController.findByNameLowerContaining
);

export default router;
