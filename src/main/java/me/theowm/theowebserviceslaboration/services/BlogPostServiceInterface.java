package me.theowm.theowebserviceslaboration.services;

import me.theowm.theowebserviceslaboration.entities.BlogPost;

import java.util.List;

public interface BlogPostServiceInterface {

    List<BlogPost> fetchAllBlogPosts();

    BlogPost addNewBlogPost(BlogPost blogPost);

    BlogPost updateBlogPost(int id, BlogPost blogPost);

    void deleteBlogPost(int id);

    BlogPost fetchBlogPostById(int id);


}
