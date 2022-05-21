package com.example.plantbook.controller;

import com.example.plantbook.dto.AddPlantPictureDTO;
import com.example.plantbook.dto.PictureDTO;
import com.example.plantbook.dto.PlantDTO;
import com.example.plantbook.dto.UserMapper;
import com.example.plantbook.entity.Plant;
import com.example.plantbook.logger.MyLogger;
import com.example.plantbook.service.PictureService;
import com.example.plantbook.service.PlantService;
import com.example.plantbook.service.PostService;
import com.example.plantbook.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.RolesAllowed;

/**
 * The Admin controller.
 * Handles all admin requests.
 */
@Controller
@CrossOrigin
@RolesAllowed("ROLE_ADMIN")
public class AdminController {
    private static final Logger LOGGER = MyLogger.getInstance();
    private final PictureService pictureService;
    private static final UserMapper userMapper = UserMapper.getInstance();
    private static UserService userService ;
    private final PlantService plantService;
    private final PostService postService;

    /**
     * Instantiates a new Admin controller.
     *
     * @param pictureService the picture service
     * @param plantService   the plant service
     * @param postService    the post service
     */
    public AdminController(PictureService pictureService, PlantService plantService, PostService postService) {
        this.pictureService = pictureService;
        this.plantService = plantService;
        this.postService = postService;
    }

    /**
     * Add plant.
     * Method: POST
     * body: PlantDTO(JSON) {name, description, url, price}
     * Responses: 200 - Plant added successfully
     * Responses: 500 - Internal server error or plant already exists
     *
     * @param plantDTO the plant dto (JSON)
     * @return the response entity (Plant)
     */
    @PostMapping("/addPlant")
    public ResponseEntity<Plant> addPlant(@RequestBody PlantDTO plantDTO) {
        LOGGER.info("addPlant");
        try{
        Plant plant = plantService.addPlant(plantDTO);
        return ResponseEntity.status(200).body(plant);
        }catch (Exception e){
            LOGGER.error("addPlant", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Add plant picture.
     * Method: POST
     * body: AddPlantPictureDTO(JSON) {url, id}
     * Responses: 200 - Picture added successfully
     * Responses: 500 - Internal server error or picture already exists
     *
     * @param addPlantPictureDTO the add plant picture dto
     * @return the response entity (Picture)
     */
    @PostMapping("/addPlantPicture")
    public ResponseEntity<Plant> addPlantPicture(@RequestBody AddPlantPictureDTO addPlantPictureDTO) {
        LOGGER.info("addPlantPicture");
        try{
        Plant plant = pictureService.addPicture(addPlantPictureDTO);
        return ResponseEntity.status(200).body(plant);
        }catch (Exception e){
            LOGGER.error("addPlantPicture", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
