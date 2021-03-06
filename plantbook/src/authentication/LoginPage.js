import '../App.css';
import React, {useRef, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {login as loginAction} from "../store/auth"

function LoginPage() {
    const dispatch = useDispatch();
    let navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const inputRefference = useRef(null);
    const inputRefference1 = useRef(null);
    const login = () => {
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                dispatch(loginAction(data))
                if(data.user.role === "USER"){
                    navigate("/user");
                    return;
                }
                else if(data.user.role === "ADMIN"){
                    navigate("/admin");
                    return;
                }else if(data.user.role ==="MODERATOR"){
                    navigate("/moderator");
                    return;
                }
                navigate("/login");
            })
            .catch(error => {
                inputRefference.current.value="";
                inputRefference1.current.value="";
                console.log(error);
            });
    }
    return (
        <div className="App">
            <header className="App-header">
                <h1> Login Page </h1>
                <label> Username:</label>
                <input
                    ref={inputRefference}
                    type="text"
                    onChange={(e) => setUsername(e.target.value)}
                    id="username"
                />
                <label> Password:</label>
                <input
                    ref={inputRefference1}
                    type="password"
                    onChange={(e) => setPassword(e.target.value)}
                    id="password1"
                />
                <p/>
                <button style={{width:"150px", height:"50px"}} onClick={login}> Login </button>
                <p/>
                <Link to="/register">Go to Register</Link>
            </header>
        </div>
    );
}

export default LoginPage;
