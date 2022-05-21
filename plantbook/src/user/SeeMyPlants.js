import React, {useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import {useAuth} from "../store";
import {useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";
import {Link} from "react-router-dom";

function SeeMyPlants() {
    const auth = useAuth();
    let navigate = useNavigate();
    const [plants, setPlants] = React.useState([]);
    const [nameFilter, setNameFilter] = useState('');

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    useEffect(() => {
        fetch('http://localhost:8080/getUserPlants', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            }
        })
            .then(res => res.json())
            .then(data => {
                setPlants(data);
                console.log(data);
            })
            .catch(err => console.log(err));
    }, []);


    return(
        <div className="App">
            <header className="App-header">
                <h1> My Plants </h1>
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
                                        <div>
                                            Owner: {plant?.user?.username ? <p style={{display:"inline"}}>{plant.user.username}</p> : <p style={{display:"inline"}}>No owner</p>}
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

export default SeeMyPlants;