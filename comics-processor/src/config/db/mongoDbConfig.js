import mongoose from "mongoose";

import * as config from "../secrets/secrets";

export function connect() {
  mongoose.connect(config.MONGO_DB_CONNECTION, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });
  mongoose.connection.on("connected", function () {
    console.log("Application successfully connected to MongoDB.");
  });
  mongoose.connection.on("error", function () {
    console.log("Error while trying to connect to MongoDB.");
  });
}
