package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.java.Category;
import fi.haagahelia.bookstore.java.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void findByNameShouldReturnCategory() {
		String a = "Front-End";
		List<Category> categories = repository.findByName(a);
		
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo(a);
	}
	
	@Test
	public void createNewCategory() {
		Category c = new Category("Sci-fi");
		repository.save(c);
		assertThat(c.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		String a = "Front-End";
		List<Category> categories = repository.findByName(a);
		repository.delete(categories.get(0).getCategoryid());
		assertThat(repository.findOne(categories.get(0).getCategoryid())).isNull();
	}
}
