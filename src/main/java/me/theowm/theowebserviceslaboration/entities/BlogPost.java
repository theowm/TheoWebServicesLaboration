package me.theowm.theowebserviceslaboration.entities;
import jakarta.persistence.*;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(nullable = false)
    private String author_id;

    public BlogPost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String authorId) {
        this.author_id = authorId;
    }
}
