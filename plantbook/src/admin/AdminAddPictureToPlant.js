import React, {useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useLocation, useNavigate} from "react-router";

function AddProfilePicture() {
    const location = useLocation();
    const {id} =location.state;
    const auth = useAuth();
    let navigate = useNavigate();
    const [url, setUrl] = React.useState('');

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    const addPlantPicture = () => {
        fetch('http://localhost:8080/addPlantPicture', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
            body: JSON.stringify(
                {
                    url: url,
                    id: id
                }
            )
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/adminViewAllPlants');
            })
            .catch(err => console.log(err));
    }

    return(
        <div className="App">
            <header className="App-header">
                <h1> Add Plant picture</h1>
                <p> Enter url for picture:</p>
                <input style={{width: "1000px"}} type="text" onChange={(e) =>
                {if(e.target.value.length<1000)
                    setUrl(e.target.value)
                else
                    e.target.value = "Max link size is 1000"}}/>
                <p/>
                <button onClick={addPlantPicture}> Add picture to plant</button>
                <p/>
            </header>
        </div>
    );
}

export default AddProfilePicture;