import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import React, {useEffect} from "react";
import {useNavigate} from "react-router";
import {useAuth} from "../store";

function ModeratorPage(){
    let navigate = useNavigate();
    const auth = useAuth();

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'MODERATOR'){
            navigate('/login');
        }
    },[])

    return (
        <div className="App">
            <header className="App-header">
                <h1> Moderator Page </h1>
                <Link to = "/MyProfile"> My Profile</Link>
                <Link to = "/moderatorViewAllPosts"> All Posts </Link>
            </header>
        </div>
    );
}

export default ModeratorPage;