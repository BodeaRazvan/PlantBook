package com.example.plantbook.controller;

import com.example.plantbook.dto.RemovePostDTO;
import com.example.plantbook.dto.UserMapper;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.RolesAllowed;

@Controller
@CrossOrigin
@RolesAllowed({"ROLE_MODERATOR","ROLE_ADMIN"})
public class ModeratorController {
    private static final Logger LOGGER = MyLogger.getInstance();
    private final PictureService pictureService;
    private static final UserMapper userMapper = UserMapper.getInstance();
    private static UserService userService ;
    private final PlantService plantService;
    private final PostService postService;

    @Autowired
    public ModeratorController(PictureService pictureService, PlantService plantService, PostService postService) {
        this.pictureService = pictureService;
        this.plantService = plantService;
        this.postService = postService;
    }

    /**
     * Remove post.
     * Method: DELETE
     * Body: RemovePostDTO(JSON) :{postId: int, details: String}
     * Responses: 200 - OK, 500/404 - Internal Server Error (post not found)
     *
     * @param removePostDTO the remove post dto
     * @return the response entity
     */
    @DeleteMapping("/removePost")
    public ResponseEntity<Post> removePost(@RequestBody RemovePostDTO removePostDTO) {
        LOGGER.info("removePost()");
        try{
            Post post = postService.removePost(removePostDTO,LoginController.getCurrentUser());
            return ResponseEntity.status(200).body(post);
        }catch (Exception e){
            LOGGER.error("removePost()",e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
