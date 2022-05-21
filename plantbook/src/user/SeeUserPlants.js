import React, {Component, useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import axios from "axios";
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useLocation, useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";

function SeeUserPlants() {
    const location = useLocation();
    const {user} =location.state;
    let navigate = useNavigate();
    const auth = useAuth();
    const [plants, setPlants] = useState([]);
    const [nameFilter, setNameFilter] = useState('');

    useEffect(() => {
        if (!auth.token) {
            navigate('/login');
        }
    }, [])

    useEffect(() => {
        axios.get(`http://localhost:8080/getUserPlants/${user.id}`, {
            headers: {
                Authorization: auth.token
            }
        })
            .then(res => {
                setPlants(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }, [])

    return(
        <div className="App">
            <header className="App-header">
                <h1> All Plants </h1>
                <p> Find by name </p>
                <input onChange={(e) => setNameFilter(e.target.value)}/>
                <ul>
                    {
                        plants.filter((plant) => {
                            if(nameFilter === ''){
                                return true;
                            }
                            return (plant.name.toLowerCase().includes(nameFilter.toLowerCase()));
                        }).reverse()
                            .map(plant =>
                                <li key={plant.id}>
                                    <div className="post">
                                        <div className="post-title">
                                            <h1>{plant.name}</h1>
                                        </div>
                                        <div className="post-content">
                                            <p>{plant.description}</p>
                                        </div>
                                        <div>
                                            Price: {plant.price}$
                                        </div>
                                        {plant.pictures.map(picture =>
                                            <div key={picture.id}>
                                                {renderPicture(picture)}
                                            </div>
                                        )}
                                    </div>
                                </li>
                            )}
                </ul>
            </header>
        </div>
    );

}

export default SeeUserPlants;