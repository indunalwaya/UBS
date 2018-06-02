package com.ubs.opsit.timeconverter.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to validate the input string only if the hours = 24h;
 * 
 * @author Indu
 *
 */
public class BerlinClockBoundaryValidator implements ClockInputValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String MAX_TIME_PATTERN = "24:[0]{2}:[0]{2}";

	public BerlinClockBoundaryValidator() {
		pattern = Pattern.compile(MAX_TIME_PATTERN);
	}

	/**
	 * Method is called whenever the input string is having hours=24. As with
	 * hours =24 only possible values for minutes and seconds are 0. So validate
	 * if given string is 24:00:00 or not. if it is return true else false.
	 * */
	@Override
	public boolean validate(String input) {
		matcher = pattern.matcher(input);
		return matcher.matches();

	}

}
