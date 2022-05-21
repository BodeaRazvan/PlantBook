import React, {Component, useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import axios from "axios";
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useLocation, useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";

function SeeUserPage() {
    const location = useLocation();
    const {user} =location.state;
    let navigate = useNavigate();
    const [profilePicture, setProfilePicture] = useState('');
    const auth = useAuth();
    const [posts, setPosts] = useState([]);
    const [plants, setPlants] = useState([]);

    useEffect(() => {
        if (!auth.token) {
            navigate('/login');
        }
    }, [])

    return(
        <div className="App">
            <header className="App-header2">
                {user.username}<br/>
                {user.email}<br/>
                {user.address}<br/>
                <div>
                    <Link to="/seeUserPosts" state={{user:user}}>
                    <button onClick={() => navigate('/seePosts')}
                            style={{
                                width:"100px",
                                height:"35px",
                                display: "inline"
                            }}
                    >
                        See Posts
                    </button>
                    </Link>
                    <span style={{marginLeft:"100px"}} />
                    <Link to="/seeUserPlants" state={{user:user}}>
                        <button
                            style={{
                                width:"100px",
                                height:"35px",
                                display: "inline"
                            }}
                        >
                            See Plants
                        </button>
                    </Link>
                </div>
                <p/>
                {renderPicture(user.profilePicture)}
            </header>
        </div>
    );
}

export default SeeUserPage;