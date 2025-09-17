package me.theowm.theowebserviceslaboration.services;

import me.theowm.theowebserviceslaboration.entities.BlogPost;
import me.theowm.theowebserviceslaboration.exceptions.ResourceNotFoundException;
import me.theowm.theowebserviceslaboration.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService implements BlogPostServiceInterface {

    //@Autowired
    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public List<BlogPost> fetchAllBlogPosts() {
        //return List.of(); detta var från början men null i videon
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost addNewBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(int id, BlogPost blogPost) {
        blogPostRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author", "id", id));
        return blogPostRepository.save(blogPost);
    }

    @Override
    public void deleteBlogPost(int id) {
        blogPostRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author", "id", id));
        blogPostRepository.deleteById(id);
    }

    @Override
    public BlogPost fetchBlogPostById(int id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", id));
    }

}
