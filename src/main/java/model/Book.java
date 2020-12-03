package model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Book {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotEmpty(message = "ko dc de trong name")
    private String name;
    @NotEmpty(message = "ko dc de trong author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book(Long id, @NotEmpty(message = "loi") String name, @NotEmpty(message = "loi") String author, Category category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
