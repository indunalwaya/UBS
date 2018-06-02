package com.ubs.opsit.timeconverter.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to validate the input time string if it is having the
 * proper format or not.
 * 
 * @author Indu
 *
 */
public class BerlinClockInputValidator implements ClockInputValidator {

	private Pattern pattern;
	private Matcher matcher;
	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9]";

	public BerlinClockInputValidator() {
		pattern = Pattern.compile(TIME24HOURS_PATTERN);
	}

	@Override
	public boolean validate(String input) {
		matcher = pattern.matcher(input);
		return matcher.matches();

	}

}
