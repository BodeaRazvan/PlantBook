import React, {useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import {useAuth} from "../store";
import {useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";
import {Link} from "react-router-dom";

function UserBuyPlant() {
    const auth = useAuth();
    let navigate = useNavigate();
    const [plants, setPlants] = React.useState([]);
    const [nameFilter, setNameFilter] = useState('');
    const [selectedPlant, setSelectedPlant] = useState(null);

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    useEffect(() => {
        fetch('http://localhost:8080/getAllPlants', {
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

    function buyPlant(selectedPlant){
        fetch('http://localhost:8080/userBuyPlant', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
            body: JSON.stringify({
                plantId: selectedPlant.id
            })
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                if(data.success){
                    alert('Plant bought successfully');
                }
                else{
                    alert('You do not have enough money');
                }
            })
            .catch(err => console.log(err));
    }

    return(
        <div className="App">
            <header className="App-header">
                <h1> All Plants </h1>
                <p> Find by name </p>
                <input onChange={(e) => setNameFilter(e.target.value)}/>
                <ul>
                    {
                        plants.filter((plant) => {
                            if(plant.user === null){
                            if(nameFilter === ''){
                                return true;
                            }
                            return (plant.name.toLowerCase().includes(nameFilter.toLowerCase()));
                            }
                        }).reverse()
                            .map(plant =>
                                <li key={plant.id}>
                                    <div className="post">
                                        <div className="post-title">
                                            <Link to="/buyPlant" state={{id : plant.id}}>
                                            <h1>{plant.name}</h1>
                                            </Link>
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

export default UserBuyPlant;