package com.example.plantbook.service;

import com.example.plantbook.dto.PlantDTO;
import com.example.plantbook.entity.Picture;
import com.example.plantbook.entity.Plant;
import com.example.plantbook.entity.User;
import com.example.plantbook.logger.MyLogger;
import com.example.plantbook.repository.PictureRepository;
import com.example.plantbook.repository.PlantRepository;
import com.example.plantbook.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Plant service.
 * Handles all the business logic for the Plant entity.
 */
@Service
public class PlantService {
    private static final Logger LOGGER = MyLogger.getInstance();

    private final PlantRepository plantRepository;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    /**
     * Instantiates a new Plant service.
     *
     * @param plantRepository   the plant repository
     * @param pictureRepository the picture repository
     * @param userRepository    the user repository
     */
    @Autowired
    public PlantService(PlantRepository plantRepository,
                        PictureRepository pictureRepository,
                        UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save plant.
     *
     * @param plant the plant to save
     * @return the plant saved
     */
    public Plant save(Plant plant) {
        LOGGER.info("Saving plant with name {}", plant.getName());
        return plantRepository.save(plant);
    }

    /**
     * Get all plants a a list.
     *
     * @param user the user for which we want to get the plants
     * @return the list of plants
     */
    public List<Plant> getPlants(User user){
        LOGGER.info("Getting plants for user with id {}", user.getId());
        return plantRepository.findByUserId(user.getId());
    }

    /**
     * Add plant.
     *
     * @param plantDTO the plant dto containing the plant to add
     * @return the plant added
     */
    @Transactional
    public Plant addPlant(PlantDTO plantDTO) {
        LOGGER.info("Adding plant with name {}", plantDTO.getName());
        Plant plant = new Plant();
        plant.setName(plantDTO.getName());
        plant.setDescription(plantDTO.getDescription());
        Picture picture = new Picture();
        picture.setUrl(plantDTO.getUrl());
        picture.setPlant(plant);
        LOGGER.info("Saving picture with url {}", picture.getUrl());
        pictureRepository.save(picture);
        plant.setPictures(List.of(picture));
        plant.setPrice(plantDTO.getPrice());
        LOGGER.info("Saving plant with name {}", plant.getName());
        return plantRepository.save(plant);
    }

    /**
     * Get all plants list.
     *
     * @return the list of found plants
     */
    public List<Plant> getAllPlants(){
        LOGGER.info("Getting all plants");
        return plantRepository.findAll();
    }

    /**
     * Get user plants list.
     *
     * @param user the user for which we want to get the plants
     * @return the list of found plants
     */
    public List<Plant> getUserPlants(User user){
        LOGGER.info("Getting plants for user with id {}", user.getId());
        return plantRepository.findByUserId(user.getId());
    }

    /**
     * Buy plant.
     *
     * @param user    the user buying the plant
     * @param plantId the plant id
     * @return the plant bought
     */
    public Plant buyPlant(User user, Long plantId){
        LOGGER.info("Buying plant with id {}", plantId);
        Plant plant = plantRepository.findById(plantId).get();
        if(user.getBalance() >= plant.getPrice()){
            user.setBalance(user.getBalance() - plant.getPrice());
            if(user.getPlants() != null){
            user.getPlants().add(plant);
            }else{
                user.setPlants(List.of(plant));
            }
            LOGGER.info("Plant with id {} bought by user  {}", plantId, user.getUsername());
            userRepository.save(user);
            plant.setUser(user);
            return plantRepository.save(plant);
        }
        return null;
    }

    /**
     * Get plant by id.
     *
     * @param id the id of the plant to get
     * @return the plant found
     */
    public Plant getPlantById(Long id){
        LOGGER.info("Getting plant with id {}", id);
        return plantRepository.findById(id).get();
    }

    /**
     * Get user plants list.
     *
     * @param userId the user id for which we want to get the plants
     * @return the list of found plants
     */
    public List<Plant> getUserPlants(Long userId){
        LOGGER.info("Getting plants for user with id {}", userId);
        return plantRepository.findByUserId(userId);
    }
}
