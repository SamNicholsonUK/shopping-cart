package com.samnicholson;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MainTest {

	private static String[] GIVEN_EXAMPLE_ONE = new String[] {"Apple","Apple","Orange","Apple"};
	private static String[] GIVEN_EXAMPLE_TWO = new String[] {"Apple,Apple,Orange,Apple"};
	private static String[] EMPTY_ARRAY = new String[0];
	private static String[] ONE_APPLE = new String[] {"Apple"};
	private static String[] ONE_ORANGE = new String[] {"Orange"};
	private static String[] ONE_APPLE_SPACE = new String[] {"Apple "};
	private static String[] ONE_ORANGE_SPACE = new String[] {"Orange "};
	private static String[] INVALID_ITEM = new String[] {"Kiwi"};
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void test_GivenExample1() {
	
		Main.main(MainTest.GIVEN_EXAMPLE_ONE);
		
		assertEquals("Incorrect outcome for the given example (1)", 
					 "Total owed: £2.05",
					 outContent.toString().trim());

	}
	
	@Test
	public void test_GivenExample2() {
	
		Main.main(MainTest.GIVEN_EXAMPLE_TWO);
		
		assertEquals("Incorrect outcome for the given example (2)", 
					 "Total owed: £2.05",
					 outContent.toString().trim());

	}
	
	@Test
	public void test_NoItems() {
	
		Main.main(MainTest.EMPTY_ARRAY);
		
		assertEquals("Incorrect outcome when no items", 
					 "Your shopping cart is empty.", 
					 outContent.toString().trim());

	}
	
	@Test
	public void test_OneApple() {
	
		Main.main(MainTest.ONE_APPLE);
		
		assertEquals("Incorrect outcome for one apple", 
					 "Total owed: £0.60", 
					 outContent.toString().trim());

	}
	
	@Test
	public void test_OneAppleSpace() {
		Main.main(MainTest.ONE_APPLE_SPACE	);
		
		assertEquals("Incorrect outcome for one apple with a space", 
					 "Total owed: £0.60", 
					 outContent.toString().trim());
	}
	
	@Test
	public void test_OneOrange() {
	
		Main.main(MainTest.ONE_ORANGE);
		
		assertEquals("Incorrect outcome for one orange", 
					 "Total owed: £0.25", 
					 outContent.toString().trim());

	}
	
	@Test
	public void test_OneOrangeSpace() {
	
		Main.main(MainTest.ONE_ORANGE_SPACE);
		
		assertEquals("Incorrect outcome for one orange with a space", 
					 "Total owed: £0.25", 
					 outContent.toString().trim());

	}
	
	@Test
	public void test_InvalidItem() {
		
		try {
			Main.main(MainTest.INVALID_ITEM);
		} catch (RuntimeException rte) {
			assertTrue("Incorrect outcome for an invalid item", 
					   rte.getMessage().contains("Unexpected item (Kiwi) in bagging area!"));
		} catch( Exception e) {
			fail();
		}

	}

}
