package assignment4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateTest {
	Date test;
	Date comparison;
	
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
	public void validAllConstructor() {
		try{
			test = new Date("nov 25 2011");
			
			comparison = new Date();
			comparison.setDay(25);
			comparison.setMonth("November");
			comparison.setYear(2011);
			
			assertEquals(test, comparison);
		}
		catch(Exception e){
			fail("The two dates were not the same");
		}
	}

}
