package cna.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import cna.data.model.Author;
import cna.data.model.Book;

@DataJpaTest
class BookRepositoryTest {
	
	@Autowired
	BookRepository  bookRepository;

	@Test
	void test() {
		bookRepository.findById(123l);
	}
	
	@Test
	void createBook(){
		
		Book book = new Book(123);
		
		Author a = new Author(123);
		
		
		bookRepository.findById(123l);
		
	}

}
