import React, {useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useLocation, useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";

function BuyPlant() {
    const location = useLocation();
    const {id} =location.state;
    const [plant, setPlant] = useState([]);
    const auth = useAuth();
    let navigate = useNavigate();

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    useEffect(() => {
        fetch('http://localhost:8080/getPlantById/' + id,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': auth.token
                }
            })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setPlant(data);
            })
    }, [])

    const buyPlant = () => {
        fetch('http://localhost:8080/userBuyPlant/' + id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            }
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/myProfile');
            })
            .catch(err => console.log(err));
    }

    return(
        <div className="App">
            <header className="App-header">
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
                </div>
                {plant?.pictures?.map(picture =>
                    <div id={picture.id} >
                        {renderPicture(picture)}
                    </div>
                )}
                <p/>

                <button style={{width:"150px", height:"50px"}} onClick={buyPlant}> Buy Plant </button>
                <p/>
            </header>
        </div>
    );
}

export default BuyPlant;