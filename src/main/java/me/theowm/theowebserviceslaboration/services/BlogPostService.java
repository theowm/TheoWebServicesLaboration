package me.theowm.theowebserviceslaboration.services;

import me.theowm.theowebserviceslaboration.entities.BlogPost;
import me.theowm.theowebserviceslaboration.exceptions.ResourceNotFoundException;
import me.theowm.theowebserviceslaboration.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;

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
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost addNewBlogPost(BlogPost blogPost, Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        String sub = token.getToken().getClaim("sub");
        blogPost.setAuthorId(sub);
        System.out.println("User with id: " + sub + " has created a new blog post");
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(int id, BlogPost blogPost, Authentication authentication) {
        BlogPost existingPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", id));

        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        String sub = token.getToken().getClaim("sub");

        boolean isAdmin = token.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_myclient_admin"));

        if (!existingPost.getAuthorId().equals(sub) && !isAdmin) {
            throw new RuntimeException("You do not have permission to update this post");
        }

        blogPost.setId(id);
        blogPost.setAuthorId(existingPost.getAuthorId());
        return blogPostRepository.save(blogPost);
    }

    @Override
    public void deleteBlogPost(int id, Authentication authentication) {
        BlogPost existingPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", id));

        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        String sub = token.getToken().getClaim("sub");
        boolean isAdmin = token.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_myclient_admin"));

        if (!existingPost.getAuthorId().equals(sub) && !isAdmin) {
            throw new RuntimeException("You do not have permission to delete this post");
        }

        blogPostRepository.deleteById(id);
    }

    @Override
    public BlogPost fetchBlogPostById(int id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BlogPost", "id", id));
    }

}
