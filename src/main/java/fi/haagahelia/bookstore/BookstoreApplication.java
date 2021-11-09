package fi.haagahelia.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.java.Book;
import fi.haagahelia.bookstore.java.BookRepository;
import fi.haagahelia.bookstore.java.Category;
import fi.haagahelia.bookstore.java.CategoryRepository;
import fi.haagahelia.bookstore.java.User;
import fi.haagahelia.bookstore.java.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			//Create database users: admin/admin user/user
			User user1 = new User("user", "$2a$10$wEq4wXEwNxDnZpCxzlkns.wpMjWkHNvc2BsIiwtned6zfb8w6ZpaG", "user@bookstore.com", "USER");
			User usertest = new User("usertest", "$2a$10$wEq4wXEwNxDnZpCxzlkns.wpMjWkHNvc2BsIiwtned6zfb8w6ZpaG", "usertest@bookstore.com", "USER");
			User user2 = new User("admin", "$2a$10$Y5tVTbUNog7XiMdNgMKSp.AIFweHlfS/Yu1r4vhS0TlXqy2mn9bG2", "admin@bookstore.com", "ADMIN");
			urepository.save(user1);
			urepository.save(usertest);
			urepository.save(user2);
			Category c1 = new Category("Back-End");
			Category c2 = new Category("Mobile");
			Category c3 = new Category("Front-End");
			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);
			Book b1 = new Book("Server Programming", "Juha Hinkula", 2018, "12345678912", 199.9, c1);
			Book b2 = new Book("Mobile Programming", "Juha Hinkula", 2018, "12345678913", 299.9, c2);
			repository.save(b1);
			repository.save(b2);
		};
	}
}
