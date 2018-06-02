package com.ubs.opsit.timeocnverter.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.opsit.timeconverter.validator.BerlinClockInputValidator;
import com.ubs.opsit.timeconverter.validator.ClockInputValidator;

public class BerlinClockInputValidatorTest {

	private static ClockInputValidator validator;

	@BeforeClass
	public static void initialize() {
		validator = new BerlinClockInputValidator();
	}

	
	@Test
	public void validateWithInvalidData1Test(){
		assertFalse(validator.validate("sdgdgh"));
	}
	
	@Test
	public void validateWithInvalidData2Test(){
		assertFalse(validator.validate("25:00:00"));
	}
	
	@Test
	public void validateWithInvalidData3Test(){
		assertFalse(validator.validate("ss:12:78"));
	}
	
	@Test
	public void validateWithInvalidData4Test(){
		assertFalse(validator.validate("12:0:0"));
	}
	
	@Test
	public void validateWithValidDataTest(){
		assertTrue(validator.validate("12:00:00"));
	}
	@Test
	public void validateWithValidData1Test(){
		assertTrue(validator.validate("2:00:00"));
	}
}
