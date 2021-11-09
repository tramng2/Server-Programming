package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.java.Book;
import fi.haagahelia.bookstore.java.BookRepository;
import fi.haagahelia.bookstore.java.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Server Programming");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Juha Hinkula");
	}
	
	
	@Test
	public void createNewBook() {
		Book book = new Book("Server Programming 2", "Juha Hinkula", 2019, "12345678919", 149.9, new Category("Theory"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = repository.findByTitle("Server Programming");
		repository.delete(books.get(0).getId());
		assertThat(repository.findOne(books.get(0).getId())).isNull();
	}
}
