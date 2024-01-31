package cna.data.author;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;

import cna.data.model.Author;
import cna.data.model.Book;

public interface AuthorRepository extends JpaRepository<Author, Long> {


}
