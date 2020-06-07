package simpleCalculator;

public class Calculator {

	public static int Add(String number) {
		if(number.equals(null) || number.trim().isEmpty()) {
		    return 0;
		}
		else if(number.contains(",") || number.contains("\n")) {
			int sumOfNumbers = 0;
			String[] elements = number.split(",|\\n");
			for(int i = 0; i<elements.length; i++)
				sumOfNumbers+=Integer.parseInt(elements[i]);
			return sumOfNumbers;
		}
		else {
			return Integer.parseInt(number);
		}
	}
}
