package com.example.plantbook.service;

import com.example.plantbook.dto.PostDTO;
import com.example.plantbook.dto.RemovePostDTO;
import com.example.plantbook.entity.Picture;
import com.example.plantbook.entity.Post;
import com.example.plantbook.entity.User;
import com.example.plantbook.logger.MyLogger;
import com.example.plantbook.mail.MailHandler;
import com.example.plantbook.repository.PostRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The Post service.
 * Handles all the business logic for the Post entity.
 */
@Service
public class PostService {
    private static final Logger LOGGER = MyLogger.getInstance();
    @Autowired
    private MailHandler mailHandler;

    private final PictureService pictureService;
    private final PostRepository postRepository;

    /**
     * Instantiates a new Post service.
     *
     * @param pictureService the picture service
     * @param postRepository the post repository
     */
    @Autowired
    public PostService(PictureService pictureService, PostRepository postRepository) {
        this.pictureService = pictureService;
        this.postRepository = postRepository;
    }

    /**
     * Save post.
     *
     * @param post the post to save
     * @return the post saved
     */
    public Post save(Post post){
        LOGGER.info("Saving post");
        return postRepository.save(post);
    }

    /**
     * Get posts list.
     *
     * @param user the user to get posts for
     * @return the list of posts
     */
    public List<Post> getPosts (User user){
        LOGGER.info("Getting posts");
        return postRepository.findByUserId(user.getId());
    }

    /**
     * Make post.
     *
     * @param postDTO the post dto containing the post data
     * @param user    the user posting
     * @return the post made
     * @throws NoSuchElementException the no such element exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Transactional
    public Post makePost(PostDTO postDTO, User user){
        LOGGER.info("Making post");
        Post post = new Post();
        post.setUser(user);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        LocalDateTime localDateTime = LocalDateTime.now();
        Time time = new Time(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        post.setPostedTime(time);
        post.setPostedAt(Date.valueOf(localDateTime.toLocalDate()));
        Picture picture = new Picture(postDTO.getUrl(),null,post,null);
        pictureService.save(picture);
        post.setPicture(picture);
        postRepository.save(post);
        return post;
    }

    /**
     * Get all posts list.
     *
     * @return the list of all posts
     */
    public List<Post> getAllPosts(){
        LOGGER.info("Getting all posts");
        return postRepository.findAll();
    }

    /**
     * Delete post.
     *
     * @param id the id
     * @return the post deleted
     * @throws NoSuchElementException the no such element exception
     */
    public Post delete(Long id){
        LOGGER.info("Deleting post");
        try{
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
        return post;
        }catch (Exception e){
            LOGGER.error("Post not found");
            return null;
        }
    }

    /**
     * Get post owner.
     *
     * @param id the id of the post
     * @return the user who owns the post
     * @throws NoSuchElementException the no such element exception
     */
    public User getPostOwner(Long id){
        LOGGER.info("Getting post owner");
        try{
        return postRepository.findById(id).get().getUser();
        }catch (Exception e){
            LOGGER.error("Post not found");
            return null;
        }
    }

    /**
     * Remove post post.
     *
     * @param removePostDTO the remove post dto
     * @param user          the user
     * @return the post
     */
    @Transactional
    public Post removePost(RemovePostDTO removePostDTO, User user){
        LOGGER.info("Removing post");
        try{
        Post post = postRepository.findById(removePostDTO.getId()).get();
        User owner = post.getUser();
        owner.getPosts().remove(post);
        pictureService.deletePicture(post.getPicture());
        postRepository.delete(post);

        String message = "Your post has been removed by the moderator " + user.getUsername() +
                    "\nPost title: " + post.getTitle() +
                    "\nPost content: " + post.getContent() +
                    "\nPost posted at: " + post.getPostedAt() + " " + post.getPostedTime() +
                    "\nPost picture: " + post.getPicture().getUrl() +
                    "\n\nReason: " + removePostDTO.getDetails();

        mailHandler.sendMail(post.getUser().getEmail(),"Your post has been removed", message);
        LOGGER.info("Post removed");
        return post;
        }catch (Exception e){
            LOGGER.error("Post not found");
            return null;
        }
    }

    /**
     * Get user posts list.
     *
     * @param id the id
     * @return the list
     */
    public List<Post> getUserPosts(Long id){
        LOGGER.info("Getting user posts");
        return postRepository.findByUserId(id);
    }

}
