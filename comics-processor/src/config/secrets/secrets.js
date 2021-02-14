const env = process.env;
import * as envs from "../constants";

export const MONGO_DB_CONNECTION =
  env.NODE_ENV === envs.ENV_CONTAINER
    ? env.MONGO_DB_CONNECTION
    : "mongodb://localhost:27017/comics-encyclopedia";
export const KAFKA_CONNECTION =
  env.NODE_ENV === envs.ENV_CONTAINER ? env.KAFKA_SERVER : "localhost:9092";
export const KAFKA_GROUP_ID =
  env.NODE_ENV === envs.ENV_CONTAINER
    ? env.KAFKA_GROUP_ID
    : "comics_processor_group";
