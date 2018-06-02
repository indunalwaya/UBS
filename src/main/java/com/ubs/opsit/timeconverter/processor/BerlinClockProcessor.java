package com.ubs.opsit.timeconverter.processor;

/**
 * Interface is defining the different operations to perform on given string to
 * make it compatible with berlin clock display as well as different constants
 * related to berlin clock functionality.
 * 
 * @author Indu
 *
 */
public interface BerlinClockProcessor {

	public static final int NUMBER_OF_HOURS_BULB = 4;
	public static final int NUMBER_OF_MINUTES_BULB_IN_FIRST_ROW = 11;
	public static final int NUMBER_OF_MINUTES_BULB_IN_SECOND_ROW = 4;
	public static final String ZERO_HOURS_ROW_VALUE = "OOOO";
	public static final String ZERO_MINUTES_FIRST_ROW_VALUE = "OOOOOOOOOOO";
	public static final String ZERO_MINUTES_SECOND_ROW_VALUE = "OOOO";
	public static final int FIRST_ROW = 1;
	public static final int SECOND_ROW = 2;
	public static final int FIRST_ROW_BULB_MINIMUM_HOURS_OR_MINUTES = 5;
	public static final char BULB_OFF = 'O';
	public static final char HOUR_OR_QUARTER_BULB_ON = 'R';
	public static final char MINUTE_OR_SECONDS_BULB_ON = 'Y';
	public static final int QUARTER_BULB_INTERVAL = 3;
	public static final int SECONDS_BULB_INTERVAL = 2;

	/**
	 * This method is used to convert the given hours into the string
	 * representing hours in berlin clock format.
	 * 
	 * @param rowNum
	 *            either first or second for berlin clock hours display.
	 * @param hours
	 *            which are given and need to be converted.
	 * @return string representing hours in berlin clock format
	 */
	public String processHours(int rowNum, int hours);

	/**
	 * This method is used to convert the given minutes into the string
	 * representing minutes in berlin clock format.
	 * 
	 * @param rowNum
	 *            either first or second for berlin clock minutes display.
	 * @param hours
	 *            which are given and need to be converted.
	 * @return string representing minutes in berlin clock format
	 */

	public String processMinutes(int rowNum, int minutes);

	/**
	 * This metohd is used to convert the given seconds into the string
	 * representing seconds in berlin clock format.
	 * 
	 * @param seconds
	 *            which need to be converted.
	 * @return string representing seconds in berlin clock format
	 */
	public String processSeconds(int seconds);

}
