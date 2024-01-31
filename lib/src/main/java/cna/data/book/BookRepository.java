package cna.data.book;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;

import cna.data.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {


}
