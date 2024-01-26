package cna.data.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author implements Serializable{
	
	/*
	public Author() {

	}

	public Author(int id) {
		this.Id = id;
	}
*/
	@Override
	public String toString() {
		return "Author [Id=" + Id + "]";
	}

	@Id 
	//@GeneratedValue(strategy = GenerationType.IDENTITY )
	int Id;

}
