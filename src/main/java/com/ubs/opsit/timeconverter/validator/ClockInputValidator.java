package com.ubs.opsit.timeconverter.validator;

public interface ClockInputValidator {
	/**
	 * Method is used to validate the input string if it is in required format.
	 * 
	 * @param input
	 *            string which need to be validated.
	 * @return true if it is in required format else false;
	 */
	public boolean validate(String input);

}
