package rnd.mate00.twodatasources.repo1;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.mate00.twodatasources.model1.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
