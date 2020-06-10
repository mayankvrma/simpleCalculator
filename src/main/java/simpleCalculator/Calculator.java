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
				List<String> elements = new ArrayList<String>();
				String numbersWithDelimiter = StringUtils.substringAfter(number, "//");
				String customString = StringUtils.substringAfter(numbersWithDelimiter, "\n");
				int sumOfNumbers = 0;
				if(numbersWithDelimiter.contains("[")) {
					if(StringUtils.countMatches(numbersWithDelimiter, "[") > 1) {
						String multiDelimiters = resolvingDelimiters(numbersWithDelimiter.substring(numbersWithDelimiter.indexOf("["), numbersWithDelimiter.lastIndexOf("]")+1));
						elements = new ArrayList<String>(Arrays.asList(customString.split(",|\\n|"+multiDelimiters.substring(0, multiDelimiters.length()-1))));
					}else {
						String multiCharDelimiter = numbersWithDelimiter.substring(numbersWithDelimiter.indexOf("[")+1, numbersWithDelimiter.indexOf("]"));
						String metaEscapedString = escapingMetaChar(multiCharDelimiter);
						elements = new ArrayList<String>(Arrays.asList(customString.split(",|\\n|"+metaEscapedString)));
					}
				}else {
					Character customDelimiter = numbersWithDelimiter.charAt(0);
					elements = new ArrayList<String>(Arrays.asList(customString.split(",|\\n|\\"+customDelimiter)));
				}
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

	private static String resolvingDelimiters(String multiDelimiter) {
		StringBuilder delimiterStr = new StringBuilder();
		for(int i=0;i<multiDelimiter.length();i++) {
			if(multiDelimiter.charAt(i) == '[') {
				StringBuilder str = new StringBuilder();
				while(multiDelimiter.charAt(i+1) != ']') {
					i++;
					str.append(multiDelimiter.charAt(i));
				}
				String escapedStr = escapingMetaChar(str.toString());
				delimiterStr.append(escapedStr.concat("|"));
				i++;
			}
		}
		return delimiterStr.toString();
	}

	private static String escapingMetaChar(String multiCharDelimiter) {
		StringBuilder str = new StringBuilder();
		char[] delimiters = multiCharDelimiter.toCharArray();
		char[] escapedDelimiters = new char[2*delimiters.length];
		for(int i=0;i<escapedDelimiters.length;i++) {
			if(i%2 != 0)
				escapedDelimiters[i] = delimiters[(i-1)/2];
			else
				escapedDelimiters[i] = '\\';
			str.append(escapedDelimiters[i]);
		}
		
		return str.toString();
	}

	private static List<String> customCheck(List<String> element) throws negativeNotAllowedException {
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
