package cna.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cna.data.model.Book;
import cna.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/createBook")
	void createBook(@RequestBody Book book) {
		bookService.create(book);	
	}
	
	@GetMapping("/getBook/{id}")
	Book getBook(@PathVariable long id) {
		Optional<Book> book = bookService.find(id);
		
		if (book.isPresent())
		{
			System.out.println("Book found: "+book.get());
			return book.get();
		}
		
		System.out.println("Book not found");
		
		return null;
	}
	
	@GetMapping("/getAllBooks")
	List<Book> getBook() {
		return bookService.findAll();
	}
	
}
