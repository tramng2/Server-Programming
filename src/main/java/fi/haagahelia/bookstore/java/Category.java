package fi.haagahelia.bookstore.java;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	//private elements
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long categoryid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;
	private String name;
	
	//Constructors
	public Category() {}
	public Category(String name) {
		super();
		this.name = name;
	}
	//--------------------------------------
	
	//Getters and Setters
	public long getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public List<Book> getBooks() {
		return this.books;
	}
}
