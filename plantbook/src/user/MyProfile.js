import React, {useEffect, useState} from "react";
import '../App.css';
import '../index.css';
import axios from "axios";
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useNavigate} from "react-router";
import html2canvas from "html2canvas";
import jspdf from "jspdf";

function MyProfile() {
    const myRef = React.useRef(null);
    let navigate = useNavigate();
    const [profilePicture, setProfilePicture] = useState('');
    const auth = useAuth();
    const [posts, setPosts] = useState([]);
    const [plants, setPlants] = useState([]);
    const [user, setUser] = useState([]);

    useEffect(() => {
        async function getPosts() {
            axios.get('http://localhost:8080/getPosts',
                {headers: {'Authorization': auth.token}}
            )
                .then(async response => {
                    const data = await response.data;
                    setPosts(data);
                })
        }

        getPosts();
    }, [])

    useEffect(() => {
        async function getPlants() {
            axios.get('http://localhost:8080/getPlants',
                {headers: {'Authorization': auth.token}}
            )
                .then(async response => {
                    const data = await response.data;
                    setPlants(data);
                })
        }

        getPlants();
    }, [])

    useEffect(() => {
        async function getProfilePicture() {
            axios.get('http://localhost:8080/getProfilePicture',
                {headers: {'Authorization': auth.token}}
            )
                .then(async response => {
                    const data = await response.data;
                    setProfilePicture(data);
                })
        }

        getProfilePicture();
    }, [])

    useEffect(() => {
        async function getLoggedInUser() {
            axios.get('http://localhost:8080/getLoggedInUser',
                {headers: {'Authorization': auth.token}}
            )
                .then(async response => {
                    const data = await response.data;
                    setUser(data);
                })
        }

        getLoggedInUser();
    }, [])

    useEffect(() => {
        if (!auth.token) {
            navigate('/login');
        }
    }, [])

    function renderPicture() {
        if (profilePicture === "undefined") {
            return (
                <div className="profilePicture">
                    <Link to="/AddProfilePicture">
                        <button> Add Profile Picture</button>
                    </Link>
                </div>
            )
        } else {
            return (
                <div className="profilePicture">
                    <Link to="/AddProfilePicture">
                        <img className="pictureAuto" src={profilePicture} alt="profile"/>
                    </Link>
                </div>
            )
        }
    }


    const exportPdf = () => {
        const input = myRef.current;
        html2canvas(input,{logging:true,letterRendering:1,useCors:true}).then(canvas => {
            const imgWidth = 212;
            const imgHeight = canvas.height * imgWidth / canvas.width;
            const imageData = canvas.toDataURL('image/png');

            const pdf = new jspdf('p', 'mm', 'a4');
            pdf.addImage(imageData, 'PNG', 0, 0, imgWidth, imgHeight);
            pdf.save('myProfile.pdf');
        });
    }

    return (
        <div ref={myRef} className="App">
            <header className="App-header2">
                <button onClick={exportPdf}> Export PDF</button>
                {user.username}<br/>
                {user.email}<br/>
                {user.address}<br/>
                <Link to="/addBalance">
                Balance: {user.balance}$<br/>
                </Link>
                <div>
                    <button onClick={() => navigate('/seePosts')}
                        style={{
                            display: "inline"
                        }}
                    >
                        See My Posts
                    </button>
                    <span style={{marginLeft:"100px"}} />
                    <Link to="/seeMyPlants">
                    <button
                        style={{
                            display: "inline"
                        }}
                    >
                        See My Plants
                    </button>
                    </Link>
                </div>
                <div>
                    <button onClick={() => {navigate('/makePost')}}
                        style={{
                            display: "inline"
                        }}
                    >
                        Make a new Post
                    </button>
                    <span style={{marginLeft:"100px"}} />
                    <Link to="/userBuyPlant">
                    <button
                        style={{
                            display: "inline"
                        }}
                    >
                        Buy a new Plant
                    </button>
                    </Link>
                </div>
                <p/>
                {renderPicture()}
                <p/>
            </header>
        </div>
    );

}

export default MyProfile;