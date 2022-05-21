import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import React, {useEffect} from "react";
import {useNavigate} from "react-router";
import {useAuth} from "../store";

function AdminPage(){
    let navigate = useNavigate();
    const auth = useAuth();

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'ADMIN'){
            navigate('/login');
        }
    },[])

    return (
        <div className="App">
            <header className="App-header">
                <h1> Admin Page </h1>
                <Link to = "/MyProfile"> My Profile</Link>
                <Link to = "/moderatorViewAllPosts"> All Posts </Link>
                <Link to="/addPlant"> Add Plant </Link>
                <Link to="/adminViewAllPlants"> See All Plants </Link>
            </header>
        </div>
    );
}

export default AdminPage;