package com.example.plantbook.controller;

import com.example.plantbook.dto.*;
import com.example.plantbook.entity.Plant;
import com.example.plantbook.entity.Post;
import com.example.plantbook.logger.MyLogger;
import com.example.plantbook.service.PictureService;
import com.example.plantbook.service.PlantService;
import com.example.plantbook.service.PostService;
import com.example.plantbook.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * The User controller.
 * Handles all requests related to the user.
 */
@Controller
@CrossOrigin
@RolesAllowed({"ROLE_ADMIN", "ROLE_USER","ROLE_MODERATOR"})
public class UserController {
    private static final Logger LOGGER = MyLogger.getInstance();
    private final PictureService pictureService;
    private static final UserMapper userMapper = UserMapper.getInstance();
    private static UserService userService ;
    private final PlantService plantService;
    private final PostService postService;

    /**
     * Instantiates a new User controller.
     *
     * @param pictureService the picture service
     * @param userService    the user service
     * @param plantService   the plant service
     * @param postService    the post service
     */
    @Autowired
    public UserController(PictureService pictureService,
                          UserService userService,
                          PlantService plantService,
                          PostService postService) {
        this.pictureService = pictureService;
        this.userService = userService;
        this.plantService = plantService;
        this.postService = postService;
    }

    /**
     * Edit/add profile picture.
     * Path: /editProfilePicture
     * Method: POST
     * Body: PictureDTO(JSON): { url: String }
     * Responses: 200 - OK, 400 - Bad Request
     *
     * @param picture the picture DTO
     * @return the response entity
     */
    @PostMapping("/editProfilePicture")
    public ResponseEntity<PictureDTO> editProfilePicture(@RequestBody PictureDTO picture) {
        LOGGER.info("editProfilePicture()");
        try{
        pictureService.editProfilePicture(picture, LoginController.getCurrentUser());}
        catch (Exception e){
            LOGGER.error("editProfilePicture()", e);
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(picture);
    }

    /**
     * Gets profile picture of currently logged in user.
     * Path: /getProfilePicture
     * Method: GET
     * Body: null (this method uses the current user)
     * Responses: 200 - OK, 400 - Bad Request
     *
     * @return the profile picture
     */
    @GetMapping("/getProfilePicture")
    public ResponseEntity<String> getProfilePicture() {
        LOGGER.info("getProfilePicture()");
        try{
            if(LoginController.getCurrentUser().getProfilePicture() != null){
            return ResponseEntity.status(200).
                    body(pictureService.getProfilePicture(LoginController.getCurrentUser()).getUrl());}
            else{
                return ResponseEntity.ok("undefined");
            }
        }
        catch (Exception e){
            LOGGER.error("getProfilePicture()", e);
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * Gets posts.
     * Path: /getPosts
     * Method: GET
     * Body: null (this method uses the current user)
     * Responses: 200 - OK, 400 - Bad Request
     *
     * @return the posts
     */
    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getPosts() {
        LOGGER.info("getPosts()");
        try{
        return ResponseEntity.status(200).body(postService.getPosts(LoginController.getCurrentUser()));}
        catch (Exception e){
            LOGGER.error("getPosts()", e);
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * Gets plants.
     * Path: /getPlants
     * Method: GET
     * Body: null (this method uses the current user)
     * Responses: 200 - OK, 400 - Bad Request
     *
     * @return the plants
     */
    @GetMapping("/getPlants")
    public ResponseEntity<List<Plant>> getPlants() {
        LOGGER.info("getPlants()");
        try{
        return ResponseEntity.status(200).body(plantService.getPlants(LoginController.getCurrentUser()));}
        catch (Exception e){
            LOGGER.error("getPlants()", e);
            return ResponseEntity.status(400).body(null);
        }
    }

    /**
     * Gets logged in user.
     * Path: /getLoggedInUser
     * Method: GET
     * Body: null
     *
     * @return the logged in user
     */
    @GetMapping("/getLoggedInUser")
    public ResponseEntity<UserDTO> getLoggedInUser() {
        LOGGER.info("getLoggedInUser()");
        try{
        return ResponseEntity.ok(userMapper.convUserToDTO(LoginController.getCurrentUser()));}
        catch (Exception e){
            LOGGER.error("getLoggedInUser()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Delete profile picture.
     * Path: /deleteProfilePicture
     * Method: DELETE
     * Body: null (this method uses the current user)
     * Responses: 200 - OK, 400 - Bad Request
     *
     * @return the profile picture
     */
    @DeleteMapping("/deleteProfilePicture")
    public ResponseEntity<String> deleteProfilePicture() {
        LOGGER.info("deleteProfilePicture()");
        try{
        pictureService.deleteProfilePicture(LoginController.getCurrentUser());}
        catch (Exception e){
            LOGGER.error("deleteProfilePicture()", e);
            return ResponseEntity.status(500).body(null);
        }
        return ResponseEntity.status(200).body("deleted");
    }

    /**
     * Make post.
     * Path: /makePost
     * Method: POST
     * Body: PostDTO(JSON) : {title,content, pictureURL}
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param post the post
     * @return the new post
     */
    @PostMapping("/makePost")
    public ResponseEntity<Post> makePost(@RequestBody PostDTO post) {
        LOGGER.info("makePost()");
        if(post.getTitle() == null || post.getTitle().isEmpty()){
            return ResponseEntity.status(500).body(null);
        }
        if((post.getContent() == null || post.getContent().isEmpty()) &&
                (post.getUrl() == null || post.getUrl().isEmpty())){
            return ResponseEntity.status(500).body(null);
        }
        try{
        return ResponseEntity.status(200).body(postService.makePost(post, LoginController.getCurrentUser()));}
        catch (Exception e){
            LOGGER.error("makePost()", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Get all posts.
     * Path: /getAllPosts
     * Method: GET
     * Body: null
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @return all the posts
     */
    @GetMapping("/userGetAllPosts")
    public ResponseEntity<List<Post>> getAllPosts(){
        LOGGER.info("getAllPosts()");
        try{
        return ResponseEntity.ok(postService.getAllPosts());}
        catch (Exception e){
            LOGGER.error("getAllPosts()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Gets post owner.
     * Path: /getPostOwner
     * Method: GET
     * Body: RequestParam(Long) : postId
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param postId the post id
     * @return the post owner
     */
    @GetMapping("/getPostOwner")
    public ResponseEntity<UserDTO> getPostOwner(@RequestParam("postId") Long postId){
        LOGGER.info("getPostOwner()");
        try{
        return ResponseEntity.ok(userMapper.convUserToDTO(postService.getPostOwner(postId)));}
        catch (Exception e){
            LOGGER.error("getPostOwner()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }


    /**
     * Add balance.
     * Path: /addBalance
     * Method: POST
     * Body: BalanceDTO(JSON) : {amount}
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param balanceDTO the balance dto
     * @return the new balance
     */
    @PostMapping("/addBalance")
    public ResponseEntity<BalanceDTO> addBalance(@RequestBody BalanceDTO balanceDTO) {
        if(balanceDTO.getBalance() == null || balanceDTO.getBalance() <= 0){
            return ResponseEntity.status(500).body(null);
        }
        LOGGER.info("addBalance()");
        try{
        userService.addBalance(LoginController.getCurrentUser(), balanceDTO.getBalance());}
        catch (Exception e){
            LOGGER.error("addBalance()", e);
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(200).body(balanceDTO);
    }

    /**
     * Get all plants.
     * Path: /getAllPlants
     * Method: GET
     * Body: null
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @return the response entity containing all plants
     */
    @GetMapping("/getAllPlants")
    public ResponseEntity<List<Plant>> getAllPlants(){
        LOGGER.info("getAllPlants()");
        try{
        return ResponseEntity.ok(plantService.getAllPlants());}
        catch (Exception e){
            LOGGER.error("getAllPlants()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Get user plants.
     * Path: /getUserPlants
     * Method: GET
     * Body: null (this method is called from the user profile page)
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @return all the user plants
     */
    @GetMapping("/getUserPlants")
    public ResponseEntity<List<Plant>> getUserPlants(){
        LOGGER.info("getUserPlants()");
        try{
        return ResponseEntity.ok(plantService.getUserPlants(LoginController.getCurrentUser()));}
        catch (Exception e){
            LOGGER.error("getUserPlants()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Buy plant.
     * Path: /buyPlant/{plantId}
     * Method: POST
     * Body: PathVariable(Long) : plantId
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param plantId the plant id
     * @return the bought plant
     */
    @PostMapping("/userBuyPlant/{plantId}")
    public ResponseEntity<Plant> buyPlant(@PathVariable Long plantId){
        LOGGER.info("buyPlant()");
        try{
        return ResponseEntity.ok(plantService.buyPlant(LoginController.getCurrentUser(), plantId));}
        catch (Exception e){
            LOGGER.error("buyPlant()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Get plant by id.
     * Path: /getPlantById/{plantId}
     * Method: GET
     * Body: PathVariable(Long) : plantId
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param plantId the plant id
     * @return the response entity
     */
    @GetMapping("/getPlantById/{plantId}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long plantId){
        LOGGER.info("getPlantById()");
        try{
        return ResponseEntity.ok(plantService.getPlantById(plantId));}
        catch (Exception e){
            LOGGER.error("getPlantById()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Get user posts.
     * Path: /getUserPosts{userId}
     * Method: GET
     * Body: PathVariable(Long) : userId
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/getUserPosts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId){
        LOGGER.info("getUserPosts()");
        try{
        return ResponseEntity.ok(postService.getUserPosts(userId));}
        catch (Exception e){
            LOGGER.error("getUserPosts()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Get user plants.
     * Path: /getUserPlants{userId}
     * Method: GET
     * Body: PathVariable(Long) : userId
     * Responses: 200 - OK, 500 - Internal Server Error
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/getUserPlants/{userId}")
    public ResponseEntity<List<Plant>> getUserPlants(@PathVariable Long userId){
        LOGGER.info("getUserPlants()");
        try{
        return ResponseEntity.ok(plantService.getUserPlants(userId));}
        catch (Exception e){
            LOGGER.error("getUserPlants()", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
