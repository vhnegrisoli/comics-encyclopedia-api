const env = process.env;
const CONTAINER_ENV = "container";

export const MONGO_DB_CONNECTION =
  env.NODE_ENV === CONTAINER_ENV
    ? env.MONGO_DB_CONNECTION
    : "mongodb://localhost:27017/comics-encyclopedia";
export const KAFKA_CONNECTION =
  env.NODE_ENV === CONTAINER_ENV ? env.KAFKA_SERVER : "localhost:9092";
export const KAFKA_GROUP_ID = "comics_processor_group";
