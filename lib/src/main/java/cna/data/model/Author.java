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
	long Id;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

}
