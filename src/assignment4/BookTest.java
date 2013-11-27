package assignment4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookTest {
	Book test;
	Book comparison;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validStringConstructor() {
		try{
			test = new Book();
			test.setAuthor("Jennifer W");
			test.setPrice(10.00);
			test.setReleaseDate("Nov 25, 2001");
			test.setTitle("My autobiography");
			test.setType("stuff");
			
			comparison = new Book("My autobiography", "Jennifer W", "stuff", new Date("Nov 25, 2001"), 10.00);
			
			assertEquals(test, comparison);
		}
		catch(Exception e){
			e.printStackTrace();
			fail("The two Books were not the same\n" + e.getMessage());
		}
	}
	
	@Test
	public void validManualConstructor() {
		try{
			test = new Book("Book; My autobiography; Jennifer W; stuff; Nov 25, 2001; $10.00");
			
			comparison = new Book();
			comparison.setAuthor("Jennifer W");
			comparison.setPrice(10.00);
			comparison.setReleaseDate("Nov 25, 2001");
			comparison.setTitle("My autobiography");
			comparison.setType("stuff");
			
			assertEquals(test, comparison);
		}
		catch(Exception e){
			fail("The two dates were not the same");
		}
	}
	
	@Test
	public void formattedStringTest() {
		try{
			test = new Book();
			test.setAuthor("Jennifer W");
			test.setPrice(10.00);
			test.setReleaseDate("Nov 25, 2001");
			test.setTitle("My autobiography");
			test.setType("stuff");
			
			assertEquals(test.toFormattedString(), "Book; My autobiography; Jennifer W; stuff; November 25, 2001; $10.00");
		}
		catch(Exception e){
			fail("The two dates were not the same");
		}
	}
}
