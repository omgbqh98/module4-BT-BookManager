package service;

import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BookRepository;

public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book finById(Long id) {
        return  bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
    bookRepository.delete(id);
    }

    @Override
    public Iterable<Book> findAllByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Book> findAllByNameContaining(String name) {
        return bookRepository.findAllByNameContaining( name);
    }

}
