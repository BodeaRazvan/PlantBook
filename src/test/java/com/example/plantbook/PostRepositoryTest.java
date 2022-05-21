package com.example.plantbook;

import com.example.plantbook.entity.Post;
import com.example.plantbook.entity.User;
import com.example.plantbook.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void init(){
        User user = new User("user1","password1","email1","address1","ADMIN");
        User user2 = new User("user2","password2","email2","address2","USER");
        List<Post> posts = Arrays.asList(
                new Post(1L,"title1", "content1", user,null,null,null),
                new Post(2L,"title2", "content2", user2,null,null,null)
                );
        postRepository.saveAll(posts);
    }

    @AfterEach
    public void cleanUp(){
        postRepository.deleteAll();
    }

    @Test
    public void findById(){
        Post post = postRepository.findById(1L).get();
        assertThat(post.getTitle()).isEqualTo("title1");
    }

    @Test
    public void saveAllSuccess(){
        User user3 = new User("user3","password3","email3","address3","ADMIN");
        User user4 = new User("user4","password4","email4","address4","USER");
        List<Post> posts = Arrays.asList(
                new Post(3L,"title3", "content3", user3,null,null,null),
                new Post(4L,"title4", "content4", user4,null,null,null)
                );
        postRepository.saveAll(posts);
        assertThat(postRepository.findAll().size()).isEqualTo(4);
    }

    @Test
    public void findAllSuccess(){
        assertThat(postRepository.findAll().size()).isEqualTo(2);
    }


}
