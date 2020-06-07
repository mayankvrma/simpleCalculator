package simpleCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class CalculatorTest {

	@Test
	public void emptystringreturnzero() {
		assertEquals(0, Calculator.Add(""));
	}
	
	@Test
	public void singlenumbereturnnumber() {
		assertEquals(1, Calculator.Add("1"));
	}
	
	@Test
	public void addingtwonumbers() {
		assertEquals(3, Calculator.Add("1,2"));
	}
	
	@Test
	public void addingmultipledelimiters() {
		assertEquals(10, Calculator.Add("1,2\n3,4"));
	}
}
