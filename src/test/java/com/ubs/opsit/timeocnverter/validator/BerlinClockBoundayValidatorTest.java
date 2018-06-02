package com.ubs.opsit.timeocnverter.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.opsit.timeconverter.validator.BerlinClockBoundaryValidator;
import com.ubs.opsit.timeconverter.validator.ClockInputValidator;

public class BerlinClockBoundayValidatorTest {
	private static ClockInputValidator validator;

	@BeforeClass
	public static void initialize() {
		validator = new BerlinClockBoundaryValidator();
	}

	@Test
	public void validateWithInvalidDataTest() {
		assertFalse(validator.validate("24:01:01"));
	}

	@Test
	public void validateWithInvalidData1Test() {
		assertFalse(validator.validate("23:01:01"));
	}

	@Test
	public void validateWithValidDataTest() {
		assertTrue(validator.validate("24:00:00"));
	}
}
