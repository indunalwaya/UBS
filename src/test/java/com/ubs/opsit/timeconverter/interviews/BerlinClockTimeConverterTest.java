package com.ubs.opsit.timeconverter.interviews;

import static org.junit.Assert.assertTrue;

import java.util.StringJoiner;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.opsit.timeconveter.interviews.BerlinClockTimeConverter;
import com.ubs.opsit.timeconveter.interviews.TimeConverter;

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
	public void convetTimeWithValidData1Test(){
		String output=timeConveter.convertTime("00:00:00");
		StringJoiner str= new StringJoiner("\r\n");
		str.add("Y");
		str.add("OOOO");
		str.add("OOOO");
		str.add("OOOOOOOOOOO");
		str.add("OOOO");
		assertTrue(output.equals(str.toString()));
	}
	
	
	@Test
	public void convetTimeWithValidData2Test(){
		String output=timeConveter.convertTime("13:17:01");
		StringJoiner str= new StringJoiner("\r\n");
		str.add("O");
		str.add("RROO");
		str.add("RRRO");
		str.add("YYROOOOOOOO");
		str.add("YYOO");
		assertTrue(output.equals(str.toString()));
	}
	
	
	@Test
	public void convetTimeWithValidData3Test(){
		String output=timeConveter.convertTime("23:59:59");
		StringJoiner str= new StringJoiner("\r\n");
		str.add("O");
		str.add("RRRR");
		str.add("RRRO");
		str.add("YYRYYRYYRYY");
		str.add("YYYY");
		assertTrue(output.equals(str.toString()));
	}
	
	@Test
	public void convetTimeWithValidData4Test(){
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
