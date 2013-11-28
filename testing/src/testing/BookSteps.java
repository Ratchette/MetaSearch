package testing;

import org.jbehave.core.annotations.*;
import assignment4.Book;
import assignment4.Date;

public class BookSteps {
	private Book book;
	
	@Given("a book x with a title $title an author $author a release date $date"
			+ "and a price of $price")
	public void createBook(String title, String author, String date, double price){
		try {
			book = new Book(title, author, "nothing", new Date(date), price);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@When("the title is set to $newTitle")
	public void setName(String newTitle){
		try {
			book.setTitle(newTitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("Then the book is still valid")
	public void validateBook(){
		if(!Book.validateBook(book.toString()));
			throw new RuntimeException("The book is no longer valid");
	}
}
