package cna.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cna.data.BookRepository;
import cna.data.model.Book;

@Component
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public void create(Book book) {
		bookRepository.save(book);
	}
	
	public Optional<Book> find(Long bookid) {
		System.out.println("Book Id: "+bookid);
		return bookRepository.findById(bookid);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

}
