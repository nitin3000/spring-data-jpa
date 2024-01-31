package cna.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cna.data.author.AuthorRepository;
import cna.data.book.BookRepository;
import cna.data.model.Author;
import cna.data.model.Book;

@Component
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	public Book create(Book book) {
		return bookRepository.save(book);
	}
	
	public Optional<Book> find(Long bookid) {
		System.out.println("Book Id: "+bookid);
		return bookRepository.findById(bookid);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book createBookWithAuthor(Book book, String authorId) {
		Long lAuthorId = Long.valueOf(authorId);		
		Optional<Author> optionalAuthor = authorRepository.findById(lAuthorId);
		
		Set<Author> authors=new HashSet<Author>();
		authors.add(optionalAuthor.get());
		
		book.setAuthor(authors);
		return bookRepository.save(book);
		//assertTrue(newBook.getAuthor().size()==1);
	}

}
