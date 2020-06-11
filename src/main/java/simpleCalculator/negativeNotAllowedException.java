package simpleCalculator;

@SuppressWarnings("serial")
public class negativeNotAllowedException extends Exception {

	negativeNotAllowedException(String ex){
		super(ex);
	}
}
