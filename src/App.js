import './App.css';
import { useState, useEffect } from 'react';

function Login(props) {
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');

  return (
    <>
      <h2>로그인</h2>
      <div className="form">
        <p>
          <input
            className="login"
            type="text"
            name="username"
            placeholder="아이디"
            onChange={(event) => {
              setId(event.target.value);
            }}
          />
        </p>
        <p>
          <input
            className="login"
            type="password"
            name="pwd"
            placeholder="비밀번호"
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </p>
        <p>
          <input
            className="btn"
            type="submit"
            value="로그인"
            onClick={() => {
              const userData = {
                userId: id,
                userPassword: password,
              };
              fetch('http://localhost:3001/login', {
                method: 'post',
                headers: {
                  'content-type': 'application/json',
                },
                body: JSON.stringify(userData),
              })
                .then((res) => res.json())
                .then((json) => {
                  if (json.isLogin === 'True') {
                    // 로그인 성공 시 main.html로 리다이렉트
                    window.location.href = '/main.html';
                  } else {
                    alert(json.isLogin);
                  }
                });
            }}
          />
        </p>
      </div>
      <p>
        계정이 없으신가요?{' '}
        <button
          onClick={() => {
            props.setMode('SIGNIN');
          }}
        >
          회원가입
        </button>
      </p>
    </>
  );
}

function Signin(props) {
  const [id, setId] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [password2, setPassword2] = useState('');

  return (
    <>
      <h2>회원가입</h2>
      <div className="form">
        <p>
          <input
            className="login"
            type="text"
            placeholder="아이디"
            onChange={(event) => {
              setId(event.target.value);
            }}
          />
        </p>
        <p>
          <input
            className="login"
            type="text"
            placeholder="이름"
            onChange={(event) => {
              setName(event.target.value);
            }}
          />
        </p>
        <p>
          <input
            className="login"
            type="password"
            placeholder="비밀번호"
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </p>
        <p>
          <input
            className="login"
            type="password"
            placeholder="비밀번호 확인"
            onChange={(event) => {
              setPassword2(event.target.value);
            }}
          />
        </p>
        <p>
          <input
            className="btn"
            type="submit"
            value="회원가입"
            onClick={() => {
              const userData = {
                userId: id,
                userName: name,
                userPassword: password,
                userPassword2: password2,
              };
              fetch('http://localhost:3001/signin', {
                method: 'post',
                headers: {
                  'content-type': 'application/json',
                },
                body: JSON.stringify(userData),
              })
                .then((res) => res.json())
                .then((json) => {
                  if (json.isSuccess === 'True') {
                    alert('회원가입이 완료되었습니다!');
                    props.setMode('LOGIN');
                  } else {
                    alert(json.isSuccess);
                  }
                });
            }}
          />
        </p>
      </div>
      <p>
        로그인화면으로 돌아가기{' '}
        <button
          onClick={() => {
            props.setMode('LOGIN');
          }}
        >
          로그인
        </button>
      </p>
    </>
  );
}


function App() {
  const [mode, setMode] = useState('');

  useEffect(() => {
    fetch('http://localhost:3001/authcheck')
      .then((res) => res.json())
      .then((json) => {
        if (json.isLogin === 'True') {
          // 로그인 상태일 경우 main.html로 리다이렉트
          window.location.href = '/main.html';
        } else {
          setMode('LOGIN');
        }
      });
  }, []);

  let content = null;

  if (mode === 'LOGIN') {
    content = <Login setMode={setMode}></Login>;
  } else if (mode === 'SIGNIN') {
    content = <Signin setMode={setMode}></Signin>;
  }

  return (
    <>
      <div className="background">
        {content}
      </div>
    </>
  );
}

export default App;