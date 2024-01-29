package cna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cna.data.AuthorRepository;
import cna.data.model.Author;
import cna.data.model.Book;

@Component
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	public void create(Author author) {
		authorRepository.save(author);
	}

	public List<Author> findAll() {
		return authorRepository.findAll();
	}	
	
}
