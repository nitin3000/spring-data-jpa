package cna.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import cna.data.model.Author;
import cna.data.model.Book;
import jakarta.transaction.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
//@Rollback(false)
class BookRepositoryTest {
	
	@Autowired
	BookRepository  bookRepository;
	
	@Autowired
	AuthorRepository  authorRepository;

	//@Test
	void test() {
		bookRepository.findById(123l);
	}
	
	@Test
	void findBook(){
		
		Long bookId = 123l;
		Book book = new Book();
		Optional<Book> optionalBook = bookRepository.findById(bookId);	
		assertTrue(optionalBook.isPresent());
		assertTrue(optionalBook.get().getId() == bookId);
		System.out.println(optionalBook);
		
	}
	
	@Test
	void createAuthor(){
		Long authId = 124l;		
		Author author = new Author();	
		author.setId(authId);
		Author newAuthor = authorRepository.save(author);
		assertTrue(newAuthor.getId() == authId);
	}
	
	@Test
	void createBook(){
		
		Long bookId = 131l;
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		
		Long authId = 124l;		
		Optional<Author> optionalAuthor = authorRepository.findById(authId);
		
		Set<Author> authors=new HashSet<Author>();
		authors.add(optionalAuthor.get());
		
		Book book = new Book();
		book.setId(bookId);
		
		book.setAuthor(authors);
		Book newBook = bookRepository.save(book);
		assertTrue(newBook.getAuthor().size()==1);
		
	}

}
