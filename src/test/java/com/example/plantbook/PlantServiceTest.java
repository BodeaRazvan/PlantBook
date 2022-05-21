package com.example.plantbook;

import com.example.plantbook.entity.Plant;
import com.example.plantbook.entity.Post;
import com.example.plantbook.entity.User;
import com.example.plantbook.repository.PictureRepository;
import com.example.plantbook.repository.PlantRepository;
import com.example.plantbook.repository.PostRepository;
import com.example.plantbook.repository.UserRepository;
import com.example.plantbook.service.PictureService;
import com.example.plantbook.service.PlantService;
import com.example.plantbook.service.PostService;
import com.example.plantbook.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;
    @Mock
    private PictureRepository pictureRepository;
    @Mock
    private UserRepository userRepository;

    PlantService plantService;

    @BeforeEach
    public void setUp() {
        plantService = new PlantService(plantRepository, pictureRepository, userRepository);
    }

    @Test
    public void testGetPlantById() {
        setUp();
        when(plantRepository.findById(1L)).thenReturn(java.util.Optional.of(new Plant()));
        assertEquals(1L, plantService.getPlantById(1L).getId());
    }

    @Test
    public void savePlantSuccess(){
        when(plantRepository.save(any(Plant.class))).thenReturn(new Plant());
        assertEquals(1L, plantService.save(new Plant()).getId());
    }

    @Test
    public void savePlantFail(){
        when(plantRepository.save(any(Plant.class))).thenReturn(null);
        assertNull(plantService.save(new Plant()));
    }

}
