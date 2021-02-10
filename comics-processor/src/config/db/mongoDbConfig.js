import mongoose from "mongoose";

import * as config from "../secrets/secrets";

export function connect() {
  console.info(`MongoDB Connection: ${config.MONGO_DB_CONNECTION}`)
  mongoose.connect(config.MONGO_DB_CONNECTION, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });
  mongoose.connection.on("connected", function () {
    console.info("Application successfully connected to MongoDB.");
  });
  mongoose.connection.on("error", function () {
    console.error("Error while trying to connect to MongoDB.");
  });
}
