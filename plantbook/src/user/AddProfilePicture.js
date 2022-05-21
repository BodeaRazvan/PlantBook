import React, {useEffect} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useNavigate} from "react-router";

function AddProfilePicture() {
    const auth = useAuth();
    let navigate = useNavigate();
    const [url, setUrl] = React.useState('');

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    const addProfilePicture = () => {
        fetch('http://localhost:8080/editProfilePicture', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
            body: JSON.stringify(
                {
                    url: url
                }
            )
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/MyProfile');
            })
            .catch(err => console.log(err));
    }

    const deleteProfilePicture = () => {
        fetch('http://localhost:8080/deleteProfilePicture', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/MyProfile');
            })
            .catch(err => console.log(err));
    }

    return(
        <div className="App">
            <header className="App-header">
                <h1> Profile picture Settings </h1>
                <p> Enter url for picture:</p>
                <input style={{width: "1000px"}} type="text" onChange={(e) =>
                                        {if(e.target.value.length<1000)
                                            setUrl(e.target.value)
                                        else
                                            e.target.value = "Max link size is 1000"}}/>
                <p/>
                <button onClick={addProfilePicture}> Add/Change profile picture</button>
                <p/>
                <Link to="/MyProfile">
                <button onClick={deleteProfilePicture}> Delete Profile Picture</button>
                </Link>
            </header>
        </div>
    );
}

export default AddProfilePicture;