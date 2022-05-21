import {
    BrowserRouter as Router,
    Routes,
    Route,
    //Link
} from "react-router-dom";
import React from "react";
import LoginPage from "./authentication/LoginPage";
import RegisterPage from "./authentication/RegisterPage";
import AdminPage from "./admin/AdminPage";
import UserPage from "./user/UserPage";
import MyProfile from "./user/MyProfile";
import AddProfilePicture from "./user/AddProfilePicture";
import MakePost from "./user/MakePost";
import SeePosts from "./user/SeePosts";
import ModeratorPage from "./moderator/ModeratorPage";
import UserViewAllPosts from "./user/UserViewAllPosts";
import ModeratorViewAllPosts from "./moderator/ModeratorViewAllPosts";
import ModeratorRemovePost from "./moderator/ModeratorRemovePost";
import AddPlant from "./admin/AddPlant";
import AddBalance from "./user/AddBalance";
import UserViewAllPlants from "./user/UserViewAllPlants";
import AdminViewAllPlants from "./admin/AdminViewAllPlants";
import AdminAddPictureToPlant from "./admin/AdminAddPictureToPlant";
import SeeMyPlants from "./user/SeeMyPlants";
import UserBuyPlant from "./user/UserBuyPlant";
import BuyPlant from "./user/BuyPlant";
import SeeUserPlants from "./user/SeeUserPlants";
import SeeUserPosts from "./user/SeeUserPosts";
import SeeUserPage from "./user/SeeUserPage";

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/" element={<LoginPage/>} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage/>} />
            <Route path="/admin" element={<AdminPage />} />
            <Route path="/user" element={<UserPage />}/>
            <Route path="/myProfile" element={<MyProfile />}/>
            <Route path="/addProfilePicture" element={<AddProfilePicture/>}/>
            <Route path="/makePost" element={<MakePost/>}/>
            <Route path="/seePosts" element={<SeePosts/>}/>
            <Route path="/moderator" element={<ModeratorPage/>}/>
            <Route path="/userViewAllPosts" element={<UserViewAllPosts/>}/>
            <Route path="/moderatorViewAllPosts" element={<ModeratorViewAllPosts/>}/>
            <Route path="/moderatorRemovePost" element={<ModeratorRemovePost/>}/>
            <Route path="/addPlant" element={<AddPlant/>}/>
            <Route path="/addBalance" element={<AddBalance/>}/>
            <Route path="/viewAllPlants" element={<UserViewAllPlants/>}/>
            <Route path="/adminViewAllPlants" element={<AdminViewAllPlants/>}/>
            <Route path="/adminAddPictureToPlant" element={<AdminAddPictureToPlant/>}/>
            <Route path="/seeMyPlants" element={<SeeMyPlants/>}/>
            <Route path="/userBuyPlant" element={<UserBuyPlant/>}/>
            <Route path="/buyPlant" element={<BuyPlant/>}/>
            <Route path="/seeUserPage" element={<SeeUserPage/>}/>
            <Route path="/seeUserPosts" element={<SeeUserPosts/>}/>
            <Route path="/seeUserPlants" element={<SeeUserPlants/>}/>
        </Routes>
    </Router>
  );
}

export default App;
