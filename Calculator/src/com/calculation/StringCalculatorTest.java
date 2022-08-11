package com.calculation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

	@Test
	public void calculationWitEmptyValue() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("");

		assertEquals(0, total);
	}

	@Test
	public void calculationWitSingleWeight() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("10");

		assertEquals(10, total);
	}

	@Test
	public void calculateMultipleWeightsWithDefaultDelimiter() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("11,25,31");

		assertEquals(67, total);
	}

	@Test
	public void calculateMultipleWeightsWithNewLineAsDelimiter() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("10\n26\n15\n30");

		assertEquals(81, total);
	}

	@Test
	public void calculateMultipleWeightsWithNewLineAndOtherDelimiters() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("10\n22\n15*30;45%50*15");

		assertEquals(187, total);
	}

	@Test
	public void calculateMultipleWeightsWithDifferentDelimiterWithFormat() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("//[;]\n1;2;3");

		assertEquals(6, total);
	}

	@Test
	public void calculateMultipleWeightsWithDifferentDelimitersWithFormat() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("//[*][$][;]\n1;2$3*4*5$6");

		assertEquals(21, total);
	}

	@Test
	public void calculateMultipleWeightsWithAllowDelimiterOfAnyLength() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("//[***]\n1***2***3");

		assertEquals(6, total);
	}

	@Test
	public void calculateMultipleWeightsWithDelimiterLengthLongerThanOneChar() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("//[**][%%]\n11**24%%45");

		assertEquals(80, total);
	}

	@Test
	public void calculateMultipleWeightsWithMaxWeightRestriction() {
		StringCalculator stringCalculator = new StringCalculator();
		int total = stringCalculator.add("11,25,1001");

		assertEquals(36, total);
	}

	@Test
	public void calculateMultipleWeightsWithNegativeValues() {
		try {
			StringCalculator stringCalculator = new StringCalculator();
			stringCalculator.add("1\n-2,3;-4&-5");
		} catch (Exception e) {
			assertTrue(e instanceof NumberFormatException);
			System.out.println(e.getMessage());
		}
	}

}