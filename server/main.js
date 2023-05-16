// server
const express = require("express");
const app = express();
const cors = require("cors");
const bodyParser = require("body-parser");
const port = 3000;

app.get("/", (req, res) => {
  console.log("enter!!!");
  res.send("Hello World2!");
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
