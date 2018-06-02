package com.ubs.opsit.timeconveter.interviews;

import java.util.Objects;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.opsit.timeconverter.processor.BerlinClockProcessor;
import com.ubs.opsit.timeconverter.processor.BerlinClockProcessorImpl;
import com.ubs.opsit.timeconverter.validator.BerlinClockBoundaryValidator;
import com.ubs.opsit.timeconverter.validator.BerlinClockInputValidator;

/**
 * This class is used to convert the time string given in HH:mm:ss format to
 * berlin clock format.
 * 
 * @author Indu
 *
 */
public class BerlinClockTimeConverter implements TimeConverter {
	private final static int MAX_TOKENS = 3;
	private final static int SECONDS_INDEX = 2;
	private final static int MINUTES_INDEX = 1;
	private final static int HOURS_INDEX = 0;
	private static Logger LOGGER = LoggerFactory
			.getLogger(BerlinClockTimeConverter.class);

	@Override
	public String convertTime(String inputTime) {
		if (Objects.nonNull(inputTime)) {
			LOGGER.info(
					"Input time string received to convert to berlin clock format is :{}",
					inputTime);
			LOGGER.debug(
					"Going to validate the given string :{} if its in expected 'HH:mm:ss' format or not",
					inputTime);

			if (new BerlinClockInputValidator().validate(inputTime)) {
				LOGGER.debug("Input time string is in expected format.");

				String[] inputParams = inputTime.split(":");
				if (inputParams != null && inputParams.length == MAX_TOKENS) {
					int hours = Integer.parseInt(inputParams[HOURS_INDEX]);
					LOGGER.debug(
							"Need to validate the boundary conition - if hours received :{} is 24 then both minutes and seconds must be zero.",
							hours);

					if (hours != 24
							|| (hours == 24 && new BerlinClockBoundaryValidator()
									.validate(inputTime)))
						return processInputParams(inputParams);
				}

			}

		}

		LOGGER.error(
				"Passed input time string:{} is either null or not in the expected format.",
				inputTime);
		throw new IllegalArgumentException("Invalid input Time");

	}

	/**
	 * This method is used to process the tokens of given input time string and
	 * return the berlin clock representation of the same.
	 * 
	 * @param inputParams
	 *            tokens having values for hours , minutes and seconds.
	 * @return berlin clock representation of input tokens.
	 */
	private String processInputParams(String[] inputParams) {
		LOGGER.debug(
				"Hours received:{}, minutes recevied :{} and seconds received:{}",
				inputParams[0], inputParams[1], inputParams[2]);
		StringJoiner berlinClockTime = new StringJoiner("\r\n");
		for (int i = inputParams.length; i > 0; i--) {
			BerlinClockProcessor processor = new BerlinClockProcessorImpl();

			switch (i) {
			case 3:
				berlinClockTime.add(processor.processSeconds(Integer
						.parseInt(inputParams[SECONDS_INDEX])));
				break;

			case 2:
				berlinClockTime.add(processor.processHours(
						BerlinClockProcessor.FIRST_ROW,
						Integer.parseInt(inputParams[HOURS_INDEX])));
				berlinClockTime.add(processor.processHours(
						BerlinClockProcessor.SECOND_ROW,
						Integer.parseInt(inputParams[HOURS_INDEX])));
				break;
			case 1:
				berlinClockTime.add(processor.processMinutes(
						BerlinClockProcessor.FIRST_ROW,
						Integer.parseInt(inputParams[MINUTES_INDEX])));
				berlinClockTime.add(processor.processMinutes(
						BerlinClockProcessor.SECOND_ROW,
						Integer.parseInt(inputParams[MINUTES_INDEX])));
				break;

			default:
				break;
			}
		}
		LOGGER.info("Berlin clock representation of given string is:{}",
				berlinClockTime.toString());
		return berlinClockTime.toString();

	}
}
