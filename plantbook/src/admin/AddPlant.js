import React, {useEffect} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useNavigate} from "react-router";

function AddPlant() {
    const auth = useAuth();
    let navigate = useNavigate();
    const [url, setUrl] = React.useState('');
    const [title, setTitle] = React.useState('');
    const [content, setContent] = React.useState('');
    const [price, setPrice] = React.useState('');

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
            return;
        }
        if(auth.user.role !== 'ADMIN'){
            navigate('/login');
        }
    },[])


    const addPlant = () => {
        fetch('http://localhost:8080/addPlant', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth.token
            },
            body: JSON.stringify({
                url: url,
                name: title,
                description: content,
                price: price
            })
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                navigate('/adminViewAllPlants');
            })
            .catch(err => console.log(err));
    }


    return (
        <div className="App">
            <header className="App-header">
                <h1 className="App-header2"> New Post
                    <p> Name </p>
                    <input type="text"  onChange={(e) => setTitle(e.target.value)}/>
                    <p> Description</p>
                    <input type="text" style={{width:"800px"}} onChange={(e) => setContent(e.target.value)}/>
                    <p> Enter link for picture:</p>
                    <input style={{width: "1000px"}} type="text" onChange={(e) => {
                        if (e.target.value.length < 1000)
                            setUrl(e.target.value)
                        else
                            e.target.value = "Max link size is 1000"
                    }}/>
                    <p> Price </p>
                    <input type="number" min={0} onChange={(e) => setPrice(e.target.value)}/>
                    <p/>

                    <button style={{width: "200px", height: "50px"}} onClick={addPlant}> Post</button>
                </h1>
            </header>
        </div>
    );
}

export default AddPlant;