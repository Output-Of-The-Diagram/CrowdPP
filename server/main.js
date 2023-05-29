// server
const express = require("express");
const app = express();
const cors = require("cors");
const bodyParser = require("body-parser");
var mysql = require("mysql");
const port = 3000;

var db = mysql.createConnection({
  host: "127.0.0.1",
  user: "root",
  password: "wlgns620",
  database: "CrowdPP",
  port: "3306",
});

db.connect();

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// GET METHOD
// 모든 crowd 정보 리턴
app.get("/allcrowd", (req, res) => {
  console.log("hi");
  console.log("hi");
  console.log("hi");
  console.log("hi");
  db.query(`SELECT * FROM Crowd`, function (error, result) {
    if (error) {
      console.log("DB QUERY ERROR");
      console.log(error);
    }
    console.log(result);
    res.json(result);
  });
});

// 유저 개인이 속한 corwd만 리턴
app.get("/mycrowd/:userId", (req, res) => {
  const userId = req.params.userId;
  db.query(`SELECT * FROM Crowd`, function (error, result) {
    if (error) {
      console.log("DB QUERY ERROR");
      console.log(error);
    }
    res.send(result);
  });
});

// 특정 crowd에 속한 모든 유저의 정보를 리턴
app.get("/allmember/:crowdId", (req, res) => {
  const crowdId = req.params.crowdId;
  db.query(`SELECT * FROM Crowd`, function (error, result) {
    if (error) {
      console.log("DB QUERY ERROR");
      console.log(error);
    }
    res.send(result);
  });
});

// POST METHOD
// ID 중복확인 요청
app.post("/checkId", (req, res) => {
  console.log("enter checkId");

  db.query(
    `SELECT id FROM User WHERE id='${req.body.id}'`,
    function (error, result) {
      if (error) {
        console.log(error);
      } else {
        if (result == 0) {
          res.json({ msg: "able" });
        } else {
          res.json({ msg: "unable" });
        }
      }
    }
  );
  console.log(req.body);
});

// POST METHOD
// 회원가입 요청
app.post("/signup", (req, res) => {
  console.log("enter signup");
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO User(id, password, joinDate, name, gender, e_mail) VALUE ('${req.body.id}', '${req.body.pw}', '${formattedDate}', '지훈', NULL, '${req.body.email}');`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          console.log("duplicated pk");
          res.json({ msg: "duplicated" });
          return;
        } else {
          res.send(error.code);
        }
      } else {
        res.json({ msg: "success" });
      }
    }
  );
  console.log(req.body);
});

// 로그인 요청
app.post("/login", (req, res) => {
  // db.query(
  //   `SELECT id, cast(AES_DECRYPT(UNHEX(password), 'messi') as char(100)) FROM Member WHERE id='${req.body.id}'`,
  //   function (error, result) {
  //     if (error) {
  //       console.log("DB QUERY ERROR");
  //       console.log(error);
  //     }
  //     const valueArray = Object.values(result[0]);
  //     const pw = valueArray[1];
  //     if (result == 0) {
  //       res.send("notRegistered");
  //     } else {
  //       if (pw === req.body.pw) {
  //         console.log("POST LOGIN");
  //         console.log(`ACCOUNT: ${req.body.id}`);
  //         res.send("allow");
  //       } else {
  //         res.send("wrongPW");
  //       }
  //     }
  //   }
  // );
  console.log(req.body);
  console.log(req.body.id);
  res.json({ msg: "good" });
});

// crowd 생성 요청
app.post("/makecrowd", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO Member (id, password, create_date) VALUES ('${req.body.id}', HEX(AES_ENCRYPT('${req.body.pw}', 'messi')), '${formattedDate}')`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          res.send("already Exist!");
        } else {
          res.send(error.code);
        }
      }
      console.log("POST ACCOUNT");
      console.log(`${req.body.id}', '${req.body.pw}', '${formattedDate}`);

      res.send("registered!");
    }
  );
});

// 모임 가입 신청 요청
app.post("/applycrowd", (req, res) => {
  console.log(req.body);
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO Request (userID, crowdID, applyDate) VALUES ('${req.body.userId}', '${req.body.crowdId}', '${formattedDate}')`,
    function (error, result) {
      if (error) {
        if (error.code === "ER_DUP_ENTRY") {
          res.json({ msg: "duplicated" });
        } else {
          res.send(error.code);
        }
      } else {
        res.json({ msg: "success" });
      }
    }
  );
});

// 모임 신청 요청 처리(승인, 거절)
app.post("/processapply", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO Member (id, password, create_date) VALUES ('${req.body.id}', HEX(AES_ENCRYPT('${req.body.pw}', 'messi')), '${formattedDate}')`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          res.send("already Exist!");
        } else {
          res.send(error.code);
        }
      }
      console.log("POST ACCOUNT");
      console.log(`${req.body.id}', '${req.body.pw}', '${formattedDate}`);

      res.send("registered!");
    }
  );
});

// 모임 삭제하기
app.post("/deletecrowd", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO Member (id, password, create_date) VALUES ('${req.body.id}', HEX(AES_ENCRYPT('${req.body.pw}', 'messi')), '${formattedDate}')`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          res.send("already Exist!");
        } else {
          res.send(error.code);
        }
      }
      console.log("POST ACCOUNT");
      console.log(`${req.body.id}', '${req.body.pw}', '${formattedDate}`);

      res.send("registered!");
    }
  );
});

// 유저를 모임에서 강퇴하기
app.post("/kickmember", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO Member (id, password, create_date) VALUES ('${req.body.id}', HEX(AES_ENCRYPT('${req.body.pw}', 'messi')), '${formattedDate}')`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          res.send("already Exist!");
        } else {
          res.send(error.code);
        }
      }
      console.log("POST ACCOUNT");
      console.log(`${req.body.id}', '${req.body.pw}', '${formattedDate}`);

      res.send("registered!");
    }
  );
});

// 유저가 모임 탈퇴하기
app.post("/escapecrowd", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `INSERT INTO Member (id, password, create_date) VALUES ('${req.body.id}', HEX(AES_ENCRYPT('${req.body.pw}', 'messi')), '${formattedDate}')`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          res.send("already Exist!");
        } else {
          res.send(error.code);
        }
      }
      console.log("POST ACCOUNT");
      console.log(`${req.body.id}', '${req.body.pw}', '${formattedDate}`);

      res.send("registered!");
    }
  );
});

app.get("/", (req, res) => {
  console.log("enter!!!");
  res.json({
    name: "jihun",
    name2: "jihun2",
  });
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});
