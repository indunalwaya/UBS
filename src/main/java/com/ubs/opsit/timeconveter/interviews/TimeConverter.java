package com.ubs.opsit.timeconveter.interviews;

public interface TimeConverter {
	/**
	 * This method is used to convert the given time string into different
	 * representation of the time.
	 * 
	 * @param inputTime
	 *            string representing the time in hh:mm:ss format
	 * @return different representation of the given time string.
	 */
	String convertTime(String inputTime);

}
