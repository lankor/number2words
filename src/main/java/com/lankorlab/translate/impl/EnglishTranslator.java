package com.lankorlab.translate.impl;

import com.lankorlab.translate.NumberTranslator;

public class EnglishTranslator extends AbstractTranslator implements NumberTranslator {
	private static final String[] UNITS = { "zero", "one", "two", "three",
			"four", "five", "six", "seven", "eight", "nine" };
	private static final String[] ESP = { null, "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen" };
	private static final String[] TENS = { null, "ten", "twenty", "thirty",
			"forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	private static final String HUNDREDS = "hundred";
	private static final String THOUSANDS = "thousand";
	private static final String MILLIONS = "million";
	private static final String BILLIONS = "billion";
	private static final String TRILLIONS = "trillion";
	private static final String QUADRILLION = "quadrillion";
	private static final String QUINTILLION = "quintillion";
	//http://nicholasacademy.com/numbertowordconverter.html#.VXetAc9_Oko
	@Override
	public String translate(Number number) {
		validate(number);
		return translateNumber(number.longValue());
	}

	protected String translateNumber(long number) {
		if (number == 0L) {
			return UNITS[((int) number)];
		}
		
		StringBuilder numToWord = new StringBuilder();
		
		int n = init(number);
		while (number != 0L) {
			long factor;
			int word;
			long resto;
			switch (n) {
			case TRILLION:
				factor = (long) Math.pow(10, TRILLION);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, QUINTILLION);
				number = resto;
				n = init(resto);

				break;
				
			case THOUSAND_BILLION:
				factor = (long) Math.pow(10, THOUSAND_BILLION);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, QUADRILLION);
				
				number = resto;
				n = init(resto);

				break;
				
			case BILLION:
				factor = (long) Math.pow(10, BILLION);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, TRILLIONS);
				number = resto;
				n = init(resto);

				break;
				
			case THOUSAND_MILLION:
				factor = (long) Math.pow(10, THOUSAND_MILLION);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, BILLIONS);
				number = resto;
				n = init(resto);
				break;
				
			case MILLION:
				factor = (long) Math.pow(10, MILLION);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, MILLIONS);
				number = resto;
				n = init(resto);
				break;
				
			case THOUSAND:
				factor = (long)Math.pow(10, THOUSAND);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, THOUSANDS);
				
				number = resto;
				n = init(resto);
				break;
				
			case HUNDRED:
				factor = (long) Math.pow(10, HUNDRED);
				word = (int) (number / factor);
				resto = number % factor;
				getNumber(numToWord, word, resto, HUNDREDS);
				number = resto;
				n = init(resto);
				break;
				
			case TEN:
				factor = (long) Math.pow(10, TEN);
				word = (int) (number / factor);
				resto = number % factor;
				
				if ((word == 1) && (resto > 0L)) {
					numToWord.append(ESP[((int) resto)]);
					number = 0L;
				} else if ((word == 1) && (resto == 0L)) {
					numToWord.append(TENS[word]);
					number = 0L;
				} else if ((word == 2) && (resto > 0L)) {
					numToWord.append(TENS[word]);
					if (resto > 0L) {
						numToWord.append("-");
					}
					number = resto;
					n = 0;
				} else if ((word == 2) && (resto == 0L)) {
					numToWord.append(TENS[word]);
					number = 0L;
				} else if (word > 2) {
					numToWord.append(TENS[word]);
					if (resto > 0L) {
						numToWord.append("-");
					}
					number = resto;
					n = 0;
				}
				break;
				
			case UNIT:
				numToWord.append(UNITS[((int) number)]);
				number = 0L;
			}
		}
		return numToWord.toString().trim();
	}

	private void getNumber(StringBuilder numToWord, int word, long resto, String position) {
		numToWord.append(translateNumber(word).trim());
		if (!numToWord.toString().endsWith(" ")) {
			numToWord.append(" ");
		}
		numToWord.append(position);

		if (resto > 0L) {
			numToWord.append(" ");
		}
	}

}
