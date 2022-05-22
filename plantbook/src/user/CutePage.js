import React, {useEffect} from "react";
import '../App.css';
import '../index.css';
import {Link} from "react-router-dom";
import {useAuth} from "../store";
import {useNavigate} from "react-router";

function CutePage() {
    const auth = useAuth();
    let navigate = useNavigate();

    useEffect(() => {
        if(!auth.token){
            navigate('/login');
        }
    },[])

    const myStyle={
        backgroundImage:
            "url('https://c4.wallpaperflare.com/wallpaper/789/341/233/makoto-shinkai-rain-the-garden-of-words-wallpaper-preview.jpg')",
        height:'110vh',
        marginTop:'-70px',
        fontSize:'50px',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        display: 'inline-block',
    };

    const style={
        height:'110vh',
        width:'100vw',
        marginTop:'-70px',
        fontSize:'50px',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        display: 'inline-block',
    }

    const flower={
        height:'20vh',
        width:'15vw',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        marginTop:'-250px',
    }


    return(
        <view style={myStyle}>
            <img style={style} src="https://i.pinimg.com/originals/91/95/f4/9195f4dd1b69f90038f627c8af422429.gif" />
            <img style={flower} src="https://media.discordapp.net/attachments/816929699648241675/978003453014061156/dark-purple-flower-no-background-geranium-plant-blossom-pollen-transparent-png-633681.png?width=697&height=676"/>
        </view>
    );
}

export default CutePage;