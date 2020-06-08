package simpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
		assertEquals(3, Calculator.Add("2,1"));
	}
	
	@Test
	public void addingmultipledelimiters() {
		assertEquals(10, Calculator.Add("1,2\n3,4"));
	}
	
	@Test
	public void addingcustomdelimiters() {
		assertEquals(10, Calculator.Add("//;\n1;2\n3,4"));
	}
	
	@Test
	public void givingnegativenumberasinput() {
		try{
			Calculator.Add("1,2,-3,4");
		} catch(Exception ex) {
			assertEquals("negatives not allowed : [-3]", ex.getMessage());
		}
	}
	
	@Test
	public void givinginputgreaterthan1000() {
		assertEquals(6, Calculator.Add("//;\n1,2\n3;1005"));
	}
}
