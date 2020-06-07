package simpleCalculator;

public class Calculator {

	public static int Add(String number) {
		if(number.equals(null) || number.trim().isEmpty()) {
		    return 0;
		}
		else if(number.split(",").length == 2) {
			String[] arr = number.split(",");
			return Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]);
		}
		else {
			return Integer.parseInt(number);
		}
	}
}
