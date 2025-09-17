package me.theowm.theowebserviceslaboration.repositories;

import me.theowm.theowebserviceslaboration.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}
