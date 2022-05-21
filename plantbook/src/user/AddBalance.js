import React, {useEffect} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useNavigate} from "react-router";

function AddBalance() {
    const auth = useAuth();
    let navigate = useNavigate();
    const [balance, setBalance] = React.useState('');

    useEffect(() => {
        if (!auth.token) {
            navigate('/login');
        }
    }, [])

    const addBalance = () => {
        fetch('http://localhost:8080/addBalance', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
            body: JSON.stringify({
                balance: balance
            })
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/myProfile');
            })
            .catch(err => console.log(err));
    }


    return (
        <div className="App">
            <header className="App-header">
                <h1 className="App-header2"> Add Balance
                    <p> Enter The amount you want to add </p>
                    <input type="number" min={0} onChange={(e) => setBalance(e.target.value)}/>
                    <p/>
                    <button style={{width: "200px", height: "50px"}} onClick={addBalance}> Add </button>
                </h1>
            </header>
        </div>
    );
}

export default AddBalance;