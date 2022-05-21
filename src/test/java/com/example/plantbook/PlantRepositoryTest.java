package com.example.plantbook;

import com.example.plantbook.entity.Plant;
import com.example.plantbook.entity.Post;
import com.example.plantbook.entity.User;
import com.example.plantbook.repository.PlantRepository;
import com.example.plantbook.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PlantRepositoryTest {
    @Autowired
    private PlantRepository plantRepository;

    @BeforeEach
    public void setUp() {
        User user = new User("user1","password1","email1","address1","ADMIN");
        User user2 = new User("user2","password2","email2","address2","USER");
        List<Plant> plants = Arrays.asList(
                new Plant(1L,"plant1", "description1", null, user,50.0),
                new Plant(2L,"plant2", "description2", null, user,50.0)
        );
        plantRepository.saveAll(plants);
    }

    @AfterEach
    public void tearDown() {
        plantRepository.deleteAll();
    }

    @Test
    public void findByUserId() {
        List<Plant> plants = plantRepository.findByUserId(1L);
        assertEquals(2, plants.size());
    }

    @Test
    public void saveAllSuccess(){
        User user = new User("user1","password1","email1","address1","ADMIN");
        Plant plant = new Plant(3L,"plant3", "description3", null, user,50.0);
        plantRepository.saveAll(Arrays.asList(plant));
        assertThat(plantRepository.findAll()).hasSize(3);
    }

    @Test
    public void findAllSuccess(){
        assertThat(plantRepository.findAll()).hasSize(2);
    }

}
