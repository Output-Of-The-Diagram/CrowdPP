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
  password: "1005cyl1005*",
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
  console.log(req.params.userId);
  const userId = req.params.userId;
  db.query(
    // `SELECT * FROM Belong JOIN User WHERE userID = '${req.params.userId}'`,
    `SELECT c.* FROM CrowdPP.Belong AS b JOIN CrowdPP.Crowd AS c ON b.crowdID = c.id WHERE b.userID = '${req.params.userId}'`,
    function (error, result) {
      console.log(result);
      if (error) {
        console.log("DB QUERY ERROR");
        console.log(error);
      }
      res.json(result);
    }
  );
});

// 특정 crowd에 속한 모든 유저의 정보를 리턴
app.get("/allmember/:crowdId", (req, res) => {
  console.log(req.params.crowdId);
  db.query(
    `SELECT userID FROM Belong WHERE crowdID = '${req.params.crowdId}'`,
    function (error, result) {
      if (error) {
        console.log("DB QUERY ERROR");
        console.log(error);
      }
      console.log(result);
      res.json(result);
    }
  );
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
  db.query(
    `SELECT id, password FROM User WHERE id='${req.body.id}'`,
    function (error, result) {
      if (error) {
        console.log("DB QUERY ERROR");
        console.log(error);
      }
      if (result == []) {
        res.json({ msg: "notRegistered" });
      } else {
        const pw = result[0].password;
        if (pw === req.body.pw) {
          console.log("POST LOGIN");
          console.log(`ACCOUNT: ${req.body.id}`);
          res.json({ msg: "allow" });
        } else {
          res.json({ msg: "wrongPW" });
        }
      }
    }
  );
  console.log(req.body);
  console.log(req.body.id);
});

// crowd 생성 요청
app.post("/makecrowd", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");
  console.log("make");

  db.query(
    `INSERT INTO Crowd (name, genDate, description, representImage) VALUE ('${req.body.crowdName}', NOW(), '${req.body.crowdExplain}', '${req.body.crowdImage}');`,
    function (error, result) {
      if (error) {
        console.log(error);
        if (error.code === "ER_DUP_ENTRY") {
          res.json({ msg: "duplicated" });
        } else {
          res.json({ msg: error.code });
        }
      } else {
        db.query(
          `INSERT INTO Belong VALUE ('${req.body.userId}', '${result.insertId}', True);`,
          function (error, result) {
            if (error) {
              console.log(error);
              res.json({ msg: error.code });
            }
            res.json({ msg: "success" });
          }
        );
      }
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
    `INSERT INTO Request VALUES ('${req.body.userId}', '${req.body.crowdId}', '${formattedDate}')`,
    function (error, result) {
      if (error) {
        if (error.code === "ER_DUP_ENTRY") {
          res.json({ msg: "duplicated" });
        } else {
          res.json({ msg: error.code });
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
  if (req.body.isAccept === 0) {
    db.query(
      `INSERT INTO Belong VALUES ('${req.userId}, ${req.crowdId}', False);`,
      function (error, result) {
        if (error) {
          if (error.code === "ER_DUP_ENTRY") {
            res.json({ msg: "already member!" });
          } else {
            res.json({ msg: error.code });
          }
        } else {
          console.log("POST ACCOUNT");
          console.log(`'${req.body.userId}', '${req.body.crowdId}'`);
          res.json({ msg: "accepted!" });
        }
      }
    );
  }
  db.query(
    `DELETE FROM Request WHERE userId = '${req.userId}' and crowdID = '${req.crowdId}'`,
    function (error, result) {
      if (error) {
        console.log(error);
      }
      console.log("POST ACCOUNT");
      console.log(`${req.body.id}', '${req.body.pw}', '${formattedDate}`);
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
    `DELETE FROM Crowd WHERE id = ${req.body.crowdId};`,
    function (error, result) {
      if (error) {
        console.log(error);
        res.json({ msg: error.code });
      } else {
        console.log("POST ACCOUNT");
        console.log(`${req.body.crowdid}`);
        res.json({ msg: "deleted!" });
      }
    }
  );
});

// 모임의 리더인지 알려주기
app.post("/isleader", (req, res) => {
  const now = new Date();
  const utcNow = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
  const koreaTimeDiff = 9 * 60 * 60 * 1000;
  const koreaNow = new Date(utcNow + koreaTimeDiff);
  const formattedDate = koreaNow.toISOString().slice(0, 19).replace("T", " ");

  db.query(
    `SELECT EXISTS (SELECT userId FROM Belong WHERE (userId = '${req.body.userId}' and crowdId = '${req.body.crowdId}' and isLeader = 1) limit 1) AS result;`,
    function (error, result) {
      if (error) {
        console.log(error);
        res.json({ msg: error.code });
      } else {
        console.log(req.body);
        console.log(result);
        if (result[0]["result"] == 0) {
          res.json({ msg: "notLeader" });
        } else {
          const pw = result[0].password;
          res.json({ msg: "leader" });
        }
      }
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
    `DELETE FROM Belong where userId = '${req.body.userId}' and crowdId = '${req.body.crowdId}';`,
    function (error, result) {
      if (error) {
        console.log(error);
        res.send(error.code);
      } else {
        console.log("POST ACCOUNT");
        console.log(`'${req.body.userId}', '${req.body.crowdId}'`);

        res.json({ msg: "registered!" });
      }
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
