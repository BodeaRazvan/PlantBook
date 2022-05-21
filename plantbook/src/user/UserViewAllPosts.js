import React, {useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import {useAuth} from "../store";
import {useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";
import {Link} from "react-router-dom";

function UserViewAllPosts() {
    const auth = useAuth();
    let navigate = useNavigate();
    const [posts, setPosts] = React.useState([]);
    const [nameFilter, setNameFilter] = useState('');
    const [dateFilter, setDateFilter] = useState('');
    const [timeFilter, setTimeFilter] = useState('');

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    useEffect(() => {
        fetch('http://localhost:8080/userGetAllPosts', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            }
        })
            .then(res => res.json())
            .then(data => {
                setPosts(data);
                console.log(data);
            })
            .catch(err => console.log(err));
    }, []);

    function resetFilters() {
        setNameFilter('');
        setDateFilter('');
        setTimeFilter('');
    }


    return(
        <div className="App">
            <header className="App-header">
                <h1> All Posts </h1>
                <p> Find by name </p>
                <input onChange={(e) => setNameFilter(e.target.value)}/>
                <p> Posted after date</p>
                <input type="date" onChange={(e) => setDateFilter(e.target.value)}/>
                <p> Posted after time</p>
                <input type="time" onChange={(e) => setTimeFilter(e.target.value)}/>
                <button onClick={resetFilters}> Reset</button>
                <ul>
                    {
                        posts.filter((post) => {
                            if (nameFilter === '') {
                                if(dateFilter === ''){
                                    if(timeFilter === ''){
                                        return posts;
                                    }
                                    else{
                                        return post.postedTime >= timeFilter;
                                    }
                                }
                                else{
                                    if(timeFilter === ''){
                                        return post.postedAt >= dateFilter;
                                    }
                                    else{
                                        return post.postedAt >= dateFilter && post.postedTime >= timeFilter;
                                    }
                                }
                            }else{
                                if(dateFilter === ''){
                                    if(timeFilter === ''){
                                        return post.title.toLowerCase().includes(nameFilter.toLowerCase());
                                    }
                                    else{
                                        return post.title.toLowerCase().includes(nameFilter.toLowerCase()) && post.postedTime >= timeFilter;
                                    }
                                }
                                else{
                                    if(timeFilter === ''){
                                        return post.title.toLowerCase().includes(nameFilter.toLowerCase()) && post.postedAt >= dateFilter;
                                    }
                                    else{
                                        return post.title.toLowerCase().includes(nameFilter.toLowerCase()) && post.postedAt >= dateFilter && post.postedTime >= timeFilter;
                                    }
                                }
                            }
                        }).reverse()
                            .map(post =>
                                <li key={post.id}>
                                    <div className="post">
                                        <div className="post-title">
                                            <h1>{post.title}</h1>
                                        </div>
                                        <div>
                                            By:<span style={{marginLeft: "25px"}}/>
                                            <Link to = "/seeUserPage" state={{user: post.user}}>
                                              {post.user.username}
                                            </Link>
                                        </div>
                                        <div className="post-date">
                                            <p>{post.postedAt} <span style={{marginLeft: "25px"}}/>
                                                {post.postedTime}</p>
                                        </div>
                                        <div className="post-content">
                                            <p>{post.content}</p>
                                        </div>
                                        {renderPicture(post.picture)}
                                    </div>
                                </li>
                            )}
                </ul>
            </header>
        </div>
    );
}

export default UserViewAllPosts;