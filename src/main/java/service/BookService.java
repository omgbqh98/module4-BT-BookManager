package service;

import model.Book;
import model.Category;

public interface BookService {
    Iterable<Book> findAll();

    Book finById(Long id);

    void save(Book book);

    void remove(Long id);

    Iterable<Book> findAllByCategory(Category category);

    Iterable<Book> findAllByNameContaining(String name);
}
