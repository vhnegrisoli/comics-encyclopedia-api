import mongoose from "mongoose";

const Schema = mongoose.Schema;
const model = mongoose.model;

const MarvelComicsSchema = new Schema({
  characterId: String,
  publisherId: String,
  name: String,
  nameLower: String,
  powerstats: Object,
  biography: Object,
  appearance: Object,
  work: Object,
  connections: Object,
  image: Object,
});

module.exports = model("MarvelComics", MarvelComicsSchema);
