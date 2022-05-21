package com.example.plantbook;

import com.example.plantbook.entity.Post;
import com.example.plantbook.entity.User;
import com.example.plantbook.repository.PostRepository;
import com.example.plantbook.service.PictureService;
import com.example.plantbook.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    PostService postService;
    PictureService pictureService;

    @BeforeEach
    public void setUp() {
        postService = new PostService(pictureService, postRepository);
    }

    @Test
    public void savePostSuccess() {
        User user = new User("user1","password1","email1","address1","ADMIN");
        Post post = new Post(1L,"title1", "content1", user,null,null,null);

        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post savedPost = postService.save(post);
        assertEquals(savedPost.getTitle(), "title1");
    }

    @Test
    public void deletePostSuccess() {
        User user = new User("user1","password1","email1","address1","ADMIN");
        Post post = new Post(1L,"title1", "content1", user,null,null,null);

        when(postRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post savedPost = postService.delete(1L);
        assertEquals(savedPost.getTitle(), "title1");
    }

    @Test
    public void findAllPostsSuccess() {
        User user = new User("user1","password1","email1","address1","ADMIN");
        Post post = new Post(1L,"title1", "content1", user,null,null,null);

        when(postRepository.findAll()).thenReturn(java.util.Arrays.asList(post));

        java.util.List<Post> posts = postService.getAllPosts();
        assertEquals(posts.get(0).getTitle(), "title1");
    }

}
