package com.ubs.opsit.timeocnverter.processor;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.ubs.opsit.timeconverter.processor.BerlinClockProcessor;
import com.ubs.opsit.timeconverter.processor.BerlinClockProcessorImpl;

public class BerlinClockProcessorTest {

	private static BerlinClockProcessor processor;

	@BeforeClass
	public static void initialize() {
		processor = new BerlinClockProcessorImpl();
	}

	@Test
	public void processHoursWithFirstRowAndZeroHourTest() {
		String berlinClockHoursFirstRow = processor.processHours(
				BerlinClockProcessor.FIRST_ROW, 0);
		assertTrue(berlinClockHoursFirstRow
				.equals(BerlinClockProcessor.ZERO_HOURS_ROW_VALUE));
	}

	@Test
	public void processHoursWithSecondRowAndZeroHourTest() {
		String berlinClockHoursSecondRow = processor.processHours(
				BerlinClockProcessor.SECOND_ROW, 0);
		assertTrue(berlinClockHoursSecondRow
				.equals(BerlinClockProcessor.ZERO_HOURS_ROW_VALUE));
	}

	@Test
	public void processHoursWithFirstRowAndLessThan5HourTest() {
		String berlinClockHoursFirstRow = processor.processHours(
				BerlinClockProcessor.FIRST_ROW, 4);
		assertTrue(berlinClockHoursFirstRow
				.equals(BerlinClockProcessor.ZERO_HOURS_ROW_VALUE));
	}

	@Test
	public void processHoursWithFirstRowAllTurnOnTest() {
		String berlinClockHoursFirstRow = processor.processHours(
				BerlinClockProcessor.FIRST_ROW, 24);
		assertTrue(berlinClockHoursFirstRow.equals("RRRR"));
	}

	@Test
	public void processHoursWithSecondRowAllTurnOnTest() {
		String berlinClockHoursSecondRow = processor.processHours(
				BerlinClockProcessor.SECOND_ROW, 24);
		assertTrue(berlinClockHoursSecondRow.equals("RRRR"));
	}

	@Test
	public void processHoursWithFirstRowSingleTurnOnTest() {
		String berlinClockHoursFirstRow = processor.processHours(
				BerlinClockProcessor.FIRST_ROW, 7);
		assertTrue(berlinClockHoursFirstRow.equals("ROOO"));
	}

	@Test
	public void processHoursWithSecondRowTwoTurnOnTest() {
		String berlinClockHoursSecondRow = processor.processHours(
				BerlinClockProcessor.SECOND_ROW, 7);
		assertTrue(berlinClockHoursSecondRow.equals("RROO"));
	}

	@Test
	public void processMinutesWithFirstRowAndZeroMinuteTest() {
		String berlinClockMinutesFirstRow = processor.processMinutes(
				BerlinClockProcessor.FIRST_ROW, 0);
		assertTrue(berlinClockMinutesFirstRow
				.equals(BerlinClockProcessor.ZERO_MINUTES_FIRST_ROW_VALUE));
	}

	@Test
	public void processMinutesWithSecondRowAndZeroMinuteTest() {
		String berlinClockMinutesSecondRow = processor.processMinutes(
				BerlinClockProcessor.SECOND_ROW, 0);
		assertTrue(berlinClockMinutesSecondRow
				.equals(BerlinClockProcessor.ZERO_MINUTES_SECOND_ROW_VALUE));
	}

	@Test
	public void processMinutesWithFirstRowAllTurnOnTest() {
		String berlinClockMinutesFirstRow = processor.processMinutes(
				BerlinClockProcessor.FIRST_ROW, 55);
		assertTrue(berlinClockMinutesFirstRow.equals("YYRYYRYYRYY"));
	}

	@Test
	public void processMinutesWithSecondRowAllTurnOnTest() {
		String berlinClockMinutesSecondRow = processor.processMinutes(
				BerlinClockProcessor.SECOND_ROW, 59);
		assertTrue(berlinClockMinutesSecondRow.equals("YYYY"));
	}

	@Test
	public void processMinutesWithFirstRowSingleQuarterBulbTurnOnTest() {
		String berlinClockMinutesFirstRow = processor.processMinutes(
				BerlinClockProcessor.FIRST_ROW, 17);
		assertTrue(berlinClockMinutesFirstRow.equals("YYROOOOOOOO"));
	}

	@Test
	public void processMinutesWithSecondRowTwoTurnOnTest() {
		String berlinClockMinutesSecondRow = processor.processMinutes(
				BerlinClockProcessor.SECOND_ROW, 17);
		assertTrue(berlinClockMinutesSecondRow.equals("YYOO"));
	}

	@Test
	public void processSecondsWhenTurnOffTest() {
		String secondsString = processor.processSeconds(1);
		assertTrue(secondsString.equals("O"));
	}

	@Test
	public void processSecondsWhenTurnOnTest() {
		String secondsString = processor.processSeconds(0);
		assertTrue(secondsString.equals("Y"));
	}

}
