package cna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cna.data.AuthorRepository;
import cna.data.model.Author;

@Component
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	public void create(Author author) {
		authorRepository.save(author);
	}	
	
}
