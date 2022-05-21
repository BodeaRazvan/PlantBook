import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import React, {useEffect} from "react";
import {useLocation, useNavigate} from "react-router";
import {useAuth} from "../store";

function ModeratorRemovePost(){
    const location = useLocation();
    const {post} =location.state;
    let navigate = useNavigate();
    const auth = useAuth();
    const [message, setMessage] = React.useState("");

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'MODERATOR'){
            navigate('/login');
        }
    },[])

    function removePost(){
        fetch('http://localhost:8080/removePost', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
            body: JSON.stringify({
                id: post.id,
                details: message
            })
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/moderatorViewAllPosts');
            })
            .catch(err => console.log(err));
    }


    return (
        <div className="App">
            <header className="App-header">
                <h1> Remove Post "{post.title}" ? </h1>
                <p> Message: </p>
                <input style={{width: "1000px"}} onChange={(e) => setMessage(e.target.value)} type="text"/>
                <p/>
                <button onClick={removePost}> Confirm </button>
            </header>
        </div>
    );
}

export default ModeratorRemovePost;