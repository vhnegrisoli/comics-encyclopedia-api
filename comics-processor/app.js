import express from "express";

const app = express();
const env = process.env;

app.get("/", (req, res) => {
  return res.json({
    message: "OK",
  });
});

const PORT = env.PORT || 8081;

app.listen(PORT, () => {
  console.log(`Application is active and running in port ${PORT}`);
});
