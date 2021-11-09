package fi.haagahelia.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstore.java.Book;
import fi.haagahelia.bookstore.java.BookRepository;
import fi.haagahelia.bookstore.java.Category;
import fi.haagahelia.bookstore.java.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping("/index")
	public String helloIndex() {
		return "helloIndex";
	}
	
	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "bookList";
	}
	
	@RequestMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "createPage";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.delete(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		// findOne() and delete() are for 1.5.x version of springboot, ver 2 has different names
		model.addAttribute("book", repository.findOne(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editPage";
	}
	
	//RESTful service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//RESTful service to get all books
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public @ResponseBody List<Category> categoryListRest() {
		return (List<Category>) categoryRepository.findAll();
	}
	
	//RESTful service to get book by ID
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
		return repository.findOne(bookId);
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "loginPage";
	}
}
