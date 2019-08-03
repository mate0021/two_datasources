package rnd.mate00.twodatasources.repo1;

import org.springframework.data.repository.CrudRepository;
import rnd.mate00.twodatasources.model1.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
