package repository;

import model.Book;
import model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book,Long> {
    Iterable<Book> findAllByCategory(Category category);

    Iterable<Book> findAllByNameContaining(String name);
}
