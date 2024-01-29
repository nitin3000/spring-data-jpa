package cna.data.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;

@Entity
public class Book implements Serializable{
	
	/*
	public Book() {

	}


	public Book(int id) {
		this.Id = id;
	}

*/
	@Override
	public String toString() {
		return "Book [Id=" + Id + ", author=" + author + "]";
	}


	@Id 
	//@GeneratedValue(strategy = GenerationType.IDENTITY )
	long Id;
	
	
	public long getId() {
		return Id;
	}


	public void setId(long id) {
		Id = id;
	}


	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "book_authors", joinColumns= {@JoinColumn(name="book_id", referencedColumnName ="id")}, inverseJoinColumns= {@JoinColumn(name="author_id", referencedColumnName ="id")})
	Set<Author> author;


	public Set<Author> getAuthor() {
		return author;
	}


	public void setAuthor(Set<Author> author) {
		this.author = author;
	}

}
