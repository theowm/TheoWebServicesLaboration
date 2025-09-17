package me.theowm.theowebserviceslaboration.controllers;

import me.theowm.theowebserviceslaboration.entities.BlogPost;
import me.theowm.theowebserviceslaboration.repositories.BlogPostRepository;
import me.theowm.theowebserviceslaboration.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogPostRepository blogPostRepository;

    //räcker med autentiserad
    @GetMapping("/api/v2/posts")
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
        return ResponseEntity.ok(blogPostService.fetchAllBlogPosts());
    }

    @GetMapping("/api/v2/post/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable int id) {
        BlogPost post = blogPostService.fetchBlogPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/api/v2/newpost")
    public ResponseEntity<BlogPost> addNewBlogPost(@RequestBody BlogPost blogPost) {
        return new ResponseEntity<>(blogPostService.addNewBlogPost(blogPost), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v2/deletepost/{id}")
    public ResponseEntity<String> deleteBlogPostById(@PathVariable int blogPostId) {
        blogPostService.deleteBlogPost(blogPostId);
        return ResponseEntity.ok("Blog post " + blogPostId + " was deleted.");
    }

    @PutMapping("/api/v2/updatepost/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable int blogPostId, @RequestBody BlogPost blogPost) {
        return ResponseEntity.ok(blogPostService.updateBlogPost(blogPostId, blogPost));
    }

    @GetMapping("/api/v2/count")
    public ResponseEntity<Integer> getBlogPostCount() {
        Integer count = blogPostService.fetchAllBlogPosts().size();
        return ResponseEntity.ok(count);
    }

}
