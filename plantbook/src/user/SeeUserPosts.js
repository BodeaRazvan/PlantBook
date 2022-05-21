import React, {Component, useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import axios from "axios";
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useLocation, useNavigate} from "react-router";
import {renderPicture} from "../utils/RenderPicture";

function SeeUserPosts() {
    const location = useLocation();
    const {user} =location.state;
    let navigate = useNavigate();
    const auth = useAuth();
    const [posts, setPosts] = useState([]);
    const [nameFilter, setNameFilter] = useState('');
    const [dateFilter, setDateFilter] = useState('');
    const [timeFilter, setTimeFilter] = useState('');


    useEffect(() => {
        if (!auth.token) {
            navigate('/login');
        }
    }, [])

    useEffect(() => {
        axios.get(`http://localhost:8080/getUserPosts/${user.id}`, {
            headers: {
                Authorization: auth.token
            }
        })
            .then(res => {
                setPosts(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }, [])

    function resetFilters() {
        setNameFilter('');
        setDateFilter('');
        setTimeFilter('');
    }

    return (
        <div className="App">
            <header className="App-header2"> Posts
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

export default SeeUserPosts;