package com.ubs.opsit.timeconverter;

import static org.junit.Assert.assertTrue;

import java.util.StringJoiner;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.opsit.timeconveter.BerlinClockTimeConverter;
import com.ubs.opsit.timeconveter.TimeConverter;

public class BerlinClockTimeConverterTest {

	private static TimeConverter timeConveter;

	@BeforeClass
	public static void initialize() {
		timeConveter = new BerlinClockTimeConverter();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void convetTimeWithInvalidData1Test(){
		timeConveter.convertTime("time");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void convetTimeWithInvalidData2Test(){
		timeConveter.convertTime("24:01:01");
	}

	
	@Test
	public void convetTimeWithValidDataTest(){
		String output=timeConveter.convertTime("24:00:00");
		StringJoiner str= new StringJoiner("\r\n");
		str.add("Y");
		str.add("RRRR");
		str.add("RRRR");
		str.add("OOOOOOOOOOO");
		str.add("OOOO");
		assertTrue(output.equals(str.toString()));
	}

}
