package simpleCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
 
public class Calculator {

	public static int Add(String number) {
		try{
			if(number.equals(null) || number.trim().isEmpty()) {
		    return 0;
			}
			else if((number.contains(",") || number.contains("\n")) && !number.startsWith("//")) {
				int sumOfNumbers = 0;
				List<String> elements = new ArrayList<String>(Arrays.asList(number.split(",|\\n")));
				List<String> newElements = customCheck(elements);
				for(String element : newElements)
					sumOfNumbers+=Integer.parseInt(element);
				return sumOfNumbers;
			}
			else if(number.startsWith("//")) {
				String customString = StringUtils.substringAfter(number, "//");
				Character customDelimiter = customString.charAt(0); 
				int sumOfNumbers = 0;
				List<String> elements = new ArrayList<String>(Arrays.asList(customString.split(",|\\n|"+customDelimiter)));
				elements.removeAll(Arrays.asList(""));
				List<String> newElements = customCheck(elements);
				for(String element : newElements)
					sumOfNumbers+=Integer.parseInt(element);
				return sumOfNumbers;
			}
			else {
				return Integer.parseInt(number);
			}
		} catch (Exception ex) {
			System.out.println("Exception occured : " + ex.getMessage());
		}
		return 0;
	}
	
	public static List<String> customCheck(List<String> element) throws negativeNotAllowedException {
		List<String> negativeNumbers = new ArrayList<String>();
		List<String> trimmedList = new ArrayList<String>();
		for(String token : element) {
			if(Integer.parseInt(token) < 0) {
			negativeNumbers.add(token);
			}
			if(Integer.parseInt(token) < 1001) {
				trimmedList.add(token);
			}
		}
		if(!negativeNumbers.isEmpty())
			throw new negativeNotAllowedException("negatives not allowed : " + negativeNumbers);
		return trimmedList;
	}
}
