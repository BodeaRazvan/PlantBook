import React, {useEffect} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useNavigate} from "react-router";

function UserPage() {
    const auth = useAuth();
    let navigate = useNavigate();

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    return(
        <div className="App">
            <header className="App-header">
                <h1> User Page </h1>
                <Link to = "/MyProfile"> My Profile</Link>
                <Link to = "/userViewAllPosts"> All Posts </Link>
                <Link to = "/viewAllPlants"> All Plants </Link>
            </header>
        </div>
    );
}

export default UserPage;