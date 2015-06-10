package com.lankorlab.translate.impl;

import com.lankorlab.translate.NumberTranslator;

public class EnglishTranslator implements NumberTranslator {
	private static final String[] UNIDADES = { "zero", "one", "two", "three",
			"four", "five", "six", "seven", "eight", "nine" };
	private static final String[] ESPECIALES = { null, "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen" };
	private static final String[] DECENAS = { null, "ten", "twenty", "thirty",
			"forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	private static final String CIENTOS = "hundred";
	private static final String MILES = "thousand";
	private static final String MILLONES = "million";
	private static final String BILLONES = "billion";
	private static final String TRILLONES = "trillion";
	private static final int TRILLON = 12;
	private static final int BILLON = 9;
	private static final int MILLON = 6;
	private static final int MIL = 3;
	private static final int CENTENA = 2;
	private static final int DECENA = 1;
	private static final int UNIDAD = 0;

	@Override
	public String translate(Number number) {
		if (number.longValue() < 0L) {
			throw new IllegalArgumentException(
					"El valor es incorrecto, el n�mero debe ser mayor o igual a 0");
		}
		return translateNumber(number.longValue());
	}

	private String translateNumber(long number) {
		if (number == 0L) {
			return UNIDADES[((int) number)];
		}
		StringBuilder numToWord = new StringBuilder();
		int n = init(number);
		while (number != 0L) {
			long factor;
			int word;
			long resto;
			String miles;
			switch (n) {
			case 12:
				factor = (long) Math.pow(10.0D, 12.0D);
				word = (int) (number / factor);
				resto = number % factor;

				String billones = getHundreds(word);
				numToWord.append(billones);
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				if (word == 1) {
					numToWord.append("trillion");
				} else {
					numToWord.append("trillion");
				}
				if (resto > 0L) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);

				break;
			case 9:
				factor = (long) Math.pow(10.0D, 9.0D);
				word = (int) (number / factor);
				resto = number % factor;

				String millones = getHundreds(word);
				numToWord.append(millones);
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				numToWord.append("billion");
				if (resto > 0L) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
			case 6:
				factor = (long) Math.pow(10.0D, 6.0D);
				word = (int) (number / factor);
				resto = number % factor;

				miles = getHundreds(word);
				numToWord.append(miles);
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				numToWord.append("million");
				if (resto > 0L) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
			case 3:
				factor = (long)Math.pow(10.0D, 3.0D);
				word = (int) (number / factor);
				resto = number % factor;

				miles = getHundreds(word);
				numToWord.append(miles);
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				numToWord.append("thousand");
				if (resto > 0L) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
			case 2:
				factor = (long)Math.pow(10.0D, 2.0D);
				word = (int) (number / factor);
				resto = number % factor;

				String cientos = getHundreds(word);
				numToWord.append(cientos);
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				numToWord.append("hundred");
				if (resto > 0L) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
			case 1:
				factor = (long) Math.pow(10.0D, 1.0D);
				word = (int) (number / factor);
				resto = number % factor;
				if ((word == 1) && (resto > 0L)) {
					numToWord.append(ESPECIALES[((int) resto)]);
					number = 0L;
				} else if ((word == 1) && (resto == 0L)) {
					numToWord.append(DECENAS[word]);
					number = 0L;
				} else if ((word == 2) && (resto > 0L)) {
					numToWord.append(DECENAS[word]);
					if (resto > 0L) {
						numToWord.append("-");
					}
					number = resto;
					n = 0;
				} else if ((word == 2) && (resto == 0L)) {
					numToWord.append(DECENAS[word]);
					number = 0L;
				} else if (word > 2) {
					numToWord.append(DECENAS[word]);
					if (resto > 0L) {
						numToWord.append("-");
					}
					number = resto;
					n = 0;
				}
				break;
			case 0:
				numToWord.append(UNIDADES[((int) number)]);
				number = 0L;
			}
		}
		return numToWord.toString().trim();
	}

	private int init(long number) {
		switch (String.valueOf(number).length()) {
		case 1:
			return 0;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
		case 5:
		case 6:
			return 3;
		case 7:
		case 8:
		case 9:
			return 6;
		case 10:
		case 11:
		case 12:
			return 9;
		case 13:
		case 14:
		case 15:
			return 12;
		}
		return 0;
	}

	private String getHundreds(int word) {
		return translateNumber(word).trim();
	}
	
	@Override
	public String translate(int number) {
		return translate(new Long(number));
	}

	@Override
	public String translate(long number) {
		return translate(new Long(number));
	}
}
