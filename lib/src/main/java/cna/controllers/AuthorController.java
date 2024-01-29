package cna.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cna.data.model.Author;
import cna.data.model.Book;
import cna.service.AuthorService;
import cna.service.BookService;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@PostMapping("/createAuthor")
	void createAuthor(@RequestBody Author author) {
		System.out.println(author);
		authorService.create(author);	
	}	
	
	
	@GetMapping("/getAllAuthors")
	List<Author> getAuthors() {
		return authorService.findAll();
	}
	
}
