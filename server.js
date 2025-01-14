const express = require('express');
const session = require('express-session');
const path = require('path');
const app = express();
const port = 3001;

const db = require('./lib/db');
const sessionOption = require('./lib/sessionOption');
const bodyParser = require('body-parser');
const bcrypt = require('bcrypt');


app.use(express.static(path.join(__dirname, './project')));
app.use(express.static(path.join(__dirname, './build')));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
// 초기 페이지 설정
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, '/project/main.html'));
});
/*// 모든 경로를 index.html로 리디렉트
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, '/build/index.html'));
});
*/
var MySQLStore = require('express-mysql-session')(session);
var sessionStore = new MySQLStore(sessionOption);

app.use(
  session({
    key: 'session_cookie_name',
    secret: '~',
    store: sessionStore,
    resave: false,
    saveUninitialized: false,
  })
);



// 인증 상태 확인
app.get('/authcheck', (req, res) => {
  const sendData = { isLogin: req.session.is_logined ? 'True' : 'False' };
  res.send(sendData);
});

// 로그아웃
app.get('/logout', function (req, res) {
  req.session.destroy(function (err) {
    res.redirect('/');
  });
});

// 로그인 처리
app.post('/login', (req, res) => {
  const username = req.body.userId;
  const password = req.body.userPassword;
  const sendData = { isLogin: '' };

  if (username && password) {
    db.query(
      'SELECT * FROM userTable WHERE username = ?',
      [username],
      function (error, results, fields) {
        if (error) throw error;
        if (results.length > 0) {
          bcrypt.compare(password, results[0].password, (err, result) => {
            if (result === true) {
              req.session.is_logined = true;
              req.session.nickname = username;
              req.session.save(function () {
                sendData.isLogin = 'True';
                res.send(sendData);
              });
              db.query(
                `INSERT INTO logTable (created, username, action, command, actiondetail) VALUES (NOW(), ?, 'login' , ?, ?)`,
                [req.session.nickname, '-', 'React 로그인 테스트'],
                function (error, result) {}
              );
            } else {
              sendData.isLogin = '로그인 정보가 일치하지 않습니다.';
              res.send(sendData);
            }
          });
        } else {
          sendData.isLogin = '아이디 정보가 일치하지 않습니다.';
          res.send(sendData);
        }
      }
    );
  } else {
    sendData.isLogin = '아이디와 비밀번호를 입력하세요!';
    res.send(sendData);
  }
});

// 회원가입 처리
app.post('/signin', (req, res) => {
  const username = req.body.userId;
  const name = req.body.userName; // 이름 데이터 받기
  const password = req.body.userPassword;
  const password2 = req.body.userPassword2;

  const sendData = { isSuccess: '' };

  if (username && name && password && password2) { // 이름도 체크
    db.query(
      'SELECT * FROM userTable WHERE username = ?',
      [username],
      function (error, results, fields) {
        if (error) throw error;
        if (results.length <= 0 && password === password2) {
          const hashedPassword = bcrypt.hashSync(password, 10);
          db.query(
            'INSERT INTO userTable (username, name, password) VALUES(?,?,?)', // 이름 포함
            [username, name, hashedPassword],
            function (error, data) {
              if (error) throw error;
              req.session.save(function () {
                sendData.isSuccess = 'True';
                res.send(sendData);
              });
            }
          );
        } else if (password !== password2) {
          sendData.isSuccess = '입력된 비밀번호가 서로 다릅니다.';
          res.send(sendData);
        } else {
          sendData.isSuccess = '이미 존재하는 아이디 입니다!';
          res.send(sendData);
        }
      }
    );
  } else {
    sendData.isSuccess = '아이디, 이름, 비밀번호를 입력하세요!';
    res.send(sendData);
  }
});



app.listen(port, '0.0.0.0', () => {
  console.log(`서버가 http://localhost:${port} 에서 실행 중입니다.`);
});
