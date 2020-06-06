package simpleCalculator;

public class Calculator {

	public static int Add(String number) {
		if(number.equals(null) || number.trim().isEmpty()) {
		return 0;
		}
		else {
			return Integer.parseInt(number);
		}
	}
}
