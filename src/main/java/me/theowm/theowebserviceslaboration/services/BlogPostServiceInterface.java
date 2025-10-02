package me.theowm.theowebserviceslaboration.services;

import me.theowm.theowebserviceslaboration.entities.BlogPost;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BlogPostServiceInterface {

    List<BlogPost> fetchAllBlogPosts();

    BlogPost addNewBlogPost(BlogPost blogPost, Authentication authentication);

    BlogPost updateBlogPost(int id, BlogPost blogPost, Authentication authentication);

    void deleteBlogPost(int id, Authentication authentication);

    BlogPost fetchBlogPostById(int id);


}
