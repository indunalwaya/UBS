package com.ubs.opsit.timeconverter.processor;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BerlinClockProcessorImpl implements BerlinClockProcessor {

	private static Logger LOGGER = LoggerFactory
			.getLogger(BerlinClockProcessorImpl.class);

	/**
	 * Method is used to check if the seconds bulb or quarter bulbs in minutes
	 * row should be turned on based on the given interval or not.
	 * 
	 * @param quarterBulbOrSecondsBulbIntevals
	 *            interval mentioned between different minutes - quarter bulbs
	 *            or interval after which seconds bulb should be turned on
	 * @param minuteIndexOrSeconds
	 *            bulb index which is getting process if processing minutes else
	 *            seconds passed in input if processing seconds
	 * @return true if the bulb should be turned on else false.
	 */
	private boolean isQarterBulbInMinutesRowOrSecondsEligibleToTurnOn(
			int quarterBulbOrSecondsBulbIntevals, int minuteIndexOrSeconds) {
		Predicate<Integer> predicate = p -> p
				% quarterBulbOrSecondsBulbIntevals == 0;
		return predicate.test(minuteIndexOrSeconds);
	}

	/**
	 * Method to check if the first hours row will be turned on with even a
	 * single bulb or not.
	 * 
	 * @param onThreshold
	 *            minimum hours to turn on a bulb in hour first row.
	 * @param hoursMinutesOrSeconds
	 *            hours passed in input string
	 * @return true even if row is eligible to turn on else false.
	 */
	private boolean isFirstRowEligibleToTurnOn(int onThreshold,
			int hoursMinutesOrSeconds) {
		Predicate<Integer> predicate = p -> p >= onThreshold;
		return predicate.test(hoursMinutesOrSeconds);
	}

	/**
	 * Get the number of bulbs to turn on in either hours or minutes row.
	 * 
	 * @param onThreshold
	 *            minimum hours/minutes to turn on a bulb.
	 * @param rowNum
	 *            either first or second.
	 * @param hoursOrMinutes
	 *            passed in input string.
	 * @return number of bulbs to be turned on in given row.
	 */
	private Integer getNoOfBulbsToTurnOn(int onThreshold, int rowNum,
			int hoursOrMinutes) {
		UnaryOperator<Integer> operator = t -> {
			if (rowNum == FIRST_ROW)
				return t / onThreshold;
			else
				return t % onThreshold;
		};
		return operator.apply(hoursOrMinutes);

	}

	public String processHours(int rowNum, int hours) {

		LOGGER.info("Prcoessing start for hours {} for row number :{}", hours,
				rowNum);
		String hoursToReturn = getHoursRepresentationForZeroValueOrIfFirstRowNotEligibleToTurnOn(
				rowNum, hours);
		if (hoursToReturn != null) {
			LOGGER.info(
					"Hours:{} are either zero or <5 for the row:{} so returning :{}",
					hours, rowNum, hoursToReturn);
			return hoursToReturn;
		}

		else

		{
			StringBuilder hourString = new StringBuilder();
			int noOfBulbsToTurnOn = getNoOfBulbsToTurnOn(
					FIRST_ROW_BULB_MINIMUM_HOURS_OR_MINUTES, rowNum, hours);
			LOGGER.debug(
					"Number of bulbs to turn on for given hours :{} for row :{} are {} ",
					hours, rowNum, noOfBulbsToTurnOn);

			for (int i = 0; i < NUMBER_OF_HOURS_BULB; i++) {
				if (i < noOfBulbsToTurnOn) {
					hourString.append(HOUR_OR_QUARTER_BULB_ON);
				} else
					hourString.append(BULB_OFF);

			}
			LOGGER.info(
					"Berlin clock representation for given hours:{} for row:{} is:{}",
					hours, rowNum, hourString);
			return hourString.toString();
		}

	}

	public String processMinutes(int rowNum, int minutes) {
		LOGGER.info("Prcoessing start for minutes {} for row number :{}",
				minutes, rowNum);
		String minutesToReturn = getMinutesRepresentationForZeroValue(rowNum,
				minutes);
		if (minutesToReturn != null) {
			LOGGER.info("Minutes:{} are  zero for the row:{} so returning :{}",
					minutes, rowNum, minutesToReturn);
			return minutesToReturn;
		}

		else {

			int noOfBulbsToTurnOn = getNoOfBulbsToTurnOn(
					FIRST_ROW_BULB_MINIMUM_HOURS_OR_MINUTES, rowNum, minutes);

			LOGGER.debug(
					"Number of bulbs to turn on for given minutes :{} for row :{} are {} ",
					minutes, rowNum, noOfBulbsToTurnOn);

			int noOfBulbsToLook = getNoOfMinutesBulbToLook(rowNum);

			LOGGER.debug(
					"Number of bulbs to look upon for give minutes:{} for row:{} are :{}",
					minutes, rowNum, noOfBulbsToLook);
			StringBuilder minuteString = new StringBuilder();
			for (int i = 0; i < noOfBulbsToLook; i++) {
				if (i < noOfBulbsToTurnOn) {
					minuteString
							.append(getValueToReturnToMakeMinutBulbToTurnOn(i,
									rowNum));
				} else
					minuteString.append(BULB_OFF);

			}
			LOGGER.info(
					"Berlin clock representation for given minutes:{} for row:{} is:{}",
					minutes, rowNum, minuteString);
			return minuteString.toString();
		}

	}

	/**
	 * Default string representation of berlin clock when passed hours = 0 or
	 * hours is <5 if given row is first.
	 * 
	 * @param rowNum
	 *            for which string is getting generated.
	 * @param hours
	 *            passed in given string.
	 * @return berlin clock representation of given row when passed hours=0 or
	 *         <5;
	 */
	private String getHoursRepresentationForZeroValueOrIfFirstRowNotEligibleToTurnOn(
			int rowNum, int hours) {
		if (hours == 0
				|| (rowNum == FIRST_ROW && !isFirstRowEligibleToTurnOn(
						FIRST_ROW_BULB_MINIMUM_HOURS_OR_MINUTES, hours)))
			return ZERO_HOURS_ROW_VALUE;
		else
			return null;

	}

	/**
	 * Default string representation of given minutes into berlin clock format
	 * when passed minutes=0.
	 * 
	 * @param rowNum
	 *            either first or second.
	 * @param minutes
	 *            passed in input.
	 * @return Default string representation of given minutes into berlin clock
	 *         format.
	 */
	private String getMinutesRepresentationForZeroValue(int rowNum, int minutes) {
		if (minutes == 0) {
			if (rowNum == FIRST_ROW)
				return ZERO_MINUTES_FIRST_ROW_VALUE;
			else
				return ZERO_MINUTES_SECOND_ROW_VALUE;
		} else
			return null;

	}

	/**
	 * Method used to get the number of bulbs to traverse when processing
	 * minutes.
	 * 
	 * @param rowNum
	 *            either first or second .
	 * @return no of bulbs to traverse.
	 */
	private int getNoOfMinutesBulbToLook(int rowNum) {
		if (rowNum == FIRST_ROW)
			return NUMBER_OF_MINUTES_BULB_IN_FIRST_ROW;
		else
			return NUMBER_OF_MINUTES_BULB_IN_SECOND_ROW;
	}

	/**
	 * Method to return the color which should be used to turn on a bulb
	 * according to given row and index.
	 * 
	 * @param index
	 *            of the bulb which is under processing.
	 * @param rowNum
	 *            either first or second.
	 * @return either 'R' or 'Y'
	 */
	private char getValueToReturnToMakeMinutBulbToTurnOn(int index, int rowNum) {
		if (rowNum == FIRST_ROW
				&& isQarterBulbInMinutesRowOrSecondsEligibleToTurnOn(
						QUARTER_BULB_INTERVAL, index + 1))
			return HOUR_OR_QUARTER_BULB_ON;
		else
			return MINUTE_OR_SECONDS_BULB_ON;
	}

	@Override
	public String processSeconds(int seconds) {
		LOGGER.info("Starting to process the given seconds:{}", seconds);
		if (isQarterBulbInMinutesRowOrSecondsEligibleToTurnOn(
				SECONDS_BULB_INTERVAL, seconds)) {
			LOGGER.info(
					"Berlin clock representation for given seconds:{}  is:{}",
					seconds, String.valueOf(MINUTE_OR_SECONDS_BULB_ON));
			return String.valueOf(MINUTE_OR_SECONDS_BULB_ON);
		}

		else {
			LOGGER.info(
					"Berlin clock representation for given seconds:{}  is:{}",
					seconds, String.valueOf(BULB_OFF));
			return String.valueOf(BULB_OFF);
		}

	}
}
