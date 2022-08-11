package com.calculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {

	private static final String NEWLINE_CHARACTER = "\n";

	public int add(String numbers) {
		int total = 0;

		if (StringUtils.isNotEmpty(numbers)) {
			return total;
		}

		validateInputData(numbers);

		if (numbers.contains(NEWLINE_CHARACTER)) {
			if (numbers.startsWith("//")) {
				String[] values = numbers.split(NEWLINE_CHARACTER);
				String[] numbersArray = values[1].split(getDelimiters(values[0]));
				total = Arrays.asList(numbersArray).stream().filter(number -> StringUtils.isNotEmpty(number))
						.filter(number -> Integer.parseInt(number) < 1001).mapToInt(Integer::valueOf).sum();
			} else {
				String[] numbersArray = numbers.split(getDelimitersWithOutprefix(numbers));
				total = Arrays.asList(numbersArray).stream().filter(number -> Integer.parseInt(number) < 1001)
						.mapToInt(Integer::valueOf).sum();
			}
		} else {
			String[] numbersToCalculate = numbers.split(",");
			for (String number : numbersToCalculate) {
				if (Integer.valueOf(number) > 1000) {
					continue;
				}
				total = total + Integer.valueOf(number);
			}
		}

		return total;
	}

	private String getDelimiters(String delimiterString) {
		String delimiterValues = delimiterString.replace("/", "");
		List<String> delimitersList = new ArrayList<>();

		String[] delimitersArray = delimiterValues.split("]");

		for (String delimiter : delimitersArray) {
			String delimiterValue = delimiter.replace("[", "");
			if (!delimitersList.contains(delimiterValue)) {
				delimitersList.add(delimiterValue);
			}
		}

		return prepareDelimiterString(delimitersList);
	}

	private String getDelimitersWithOutprefix(String delimiterString) {

		List<String> delimitersList = new ArrayList<>();

		for (int i = 0; i < delimiterString.length(); i++) {
			char delimiter = delimiterString.charAt(i);
			if (!Character.isDigit(delimiter) && !delimitersList.contains(String.valueOf(delimiter))) {
				delimitersList.add(String.valueOf(delimiter));
			}
		}

		return prepareDelimiterString(delimitersList);
	}

	private String prepareDelimiterString(List<String> delimitersList) {

		StringBuilder delimiters = new StringBuilder();
		delimiters.append("[");
		delimitersList.stream().forEach(delimiter -> delimiters.append(delimiter));
		delimiters.append("]");

		return delimiters.toString();
	}

	private void validateInputData(String numbers) throws NumberFormatException {
		if (numbers.contains("-")) {
			String errorMessage = StringUtils.EMPTY;
			String[] negativeValuesArray = numbers.split("-");
			boolean skipFirstStep = true;
			for (String negativeString : negativeValuesArray) {
				if (skipFirstStep) {
					skipFirstStep = false;
					continue;
				}
				errorMessage = errorMessage + negativeString.charAt(0) + " , ";
			}
			if (StringUtils.isNotEmpty(errorMessage)) {
				throw new NumberFormatException("Negative Values are not allowed:" + errorMessage);
			}
		}
	}
}