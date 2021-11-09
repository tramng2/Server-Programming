package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.java.User;
import fi.haagahelia.bookstore.java.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User u = repository.findByUsername("user");
		
		assertThat(u.getRole()).isEqualTo("USER");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("testuser", "$2a$10$wEq4wXEwNxDnZpCxzlkns.wpMjWkHNvc2BsIiwtned6zfb8w6ZpaG", "testuser@bookstore.com", "USER");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		User u = repository.findByUsername("usertest");
		repository.delete(u.getId());
		assertThat(repository.findOne(u.getId())).isNull();
	}
}
