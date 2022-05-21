package com.example.plantbook.service;

import com.example.plantbook.dto.AddPlantPictureDTO;
import com.example.plantbook.dto.PictureDTO;
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

/**
 * Picture service
 * Handles all the picture related operations
 */
@Service
public class PictureService {
    private static final Logger LOGGER = MyLogger.getInstance();

    private final PictureRepository pictureRepository;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;

    /**
     * Instantiates a new Picture service.
     *
     * @param pictureRepository the picture repository
     * @param plantRepository   the plant repository
     * @param userRepository    the user repository
     */
    @Autowired
    public PictureService(PictureRepository pictureRepository,
                          PlantRepository plantRepository,
                          UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save picture.
     *
     * @param picture the picture to save
     * @return the picture saved
     */
    public Picture save(Picture picture) {
        LOGGER.info("Saving picture");
        return pictureRepository.save(picture);
    }

    /**
     * Delete picture.
     *
     * @param picture the picture to delete
     * @return the picture deleted
     */
    public Picture delete(Picture picture) {
        LOGGER.info("Deleting picture");
        pictureRepository.delete(picture);
        return picture;
    }

    /**
     * Edit profile picture.
     *
     * @param picture the picture we want to edit
     * @param user    the user for which we want to edit the picture
     */
    @Transactional
    public void editProfilePicture(PictureDTO picture, User user) {
        LOGGER.info("Editing profile picture");
        Picture oldPicture = user.getProfilePicture();
        user.setProfilePicture(null);
        userRepository.save(user);
        pictureRepository.delete(oldPicture);
        Picture newPicture = new Picture(picture.getUrl(),null,null,user);
        user.setProfilePicture(newPicture);
        pictureRepository.save(newPicture);
    }

    /**
     * Gets profile picture.
     *
     * @param user the user for which we want to get the picture
     * @return the profile picture of the user
     */
    @Transactional
    public Picture getProfilePicture(User user) {
        LOGGER.info("Getting profile picture");
        return user.getProfilePicture();
    }

    /**
     * Delete profile picture.
     *
     * @param user the user for which we want to delete the picture
     */
    @Transactional
    public void deleteProfilePicture(User user) {
        LOGGER.info("Deleting profile picture");
        Picture oldPicture = user.getProfilePicture();
        if (oldPicture != null) {
            user.setProfilePicture(null);
            pictureRepository.delete(oldPicture);
        }
    }

    /**
     * Delete picture.
     *
     * @param picture the picture to delete
     */
    @Transactional
    public void deletePicture(Picture picture) {
        LOGGER.info("Deleting picture");
        pictureRepository.delete(picture);
    }

    /**
     * Add picture plant.
     *
     * @param addPlantPictureDTO dto containing the plant and the picture we want to add
     * @return the plant with the picture added
     */
    @Transactional
    public Plant addPicture(AddPlantPictureDTO addPlantPictureDTO) {
        LOGGER.info("Adding picture with url {} to plant with id {}", addPlantPictureDTO.getUrl(), addPlantPictureDTO.getId());
        Plant plant = plantRepository.findById(addPlantPictureDTO.getId()).orElseThrow();
        Picture picture = new Picture();
        picture.setUrl(addPlantPictureDTO.getUrl());
        picture.setPlant(plant);
        LOGGER.info("Saving picture with url {}", picture.getUrl());
        pictureRepository.save(picture);
        plant.getPictures().add(picture);
        return plantRepository.save(plant);
    }

}
