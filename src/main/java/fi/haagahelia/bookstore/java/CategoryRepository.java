package fi.haagahelia.bookstore.java;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findByName(@Param("name") String name);
}