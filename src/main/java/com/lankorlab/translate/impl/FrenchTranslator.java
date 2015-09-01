package com.lankorlab.translate.impl;

import com.lankorlab.translate.NumberTranslator;

public class FrenchTranslator extends AbstractTranslator implements
		NumberTranslator {
	
	/**
	 * Representacion escrita de las unidades.
	 */
	private static final String[] UNITS = {"zéro", "une", "deux", "trois", 
		"quatre", "cinq", "six", "sept", "huit", "neuf"};
	
	private static final String[] ESP = {null, "onze", "douze", "treize", "quatorze", 
		"quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"};
	
	private static final String[] TENS = {null, "dix", "vingt", "trente", "quarante", 
		"cinquante", "soixante", "soixante-dix", "quatre-vingt", 
		"quatre-vingt-dix"};
	
	private static final String HUNDREDS = "cent";

	private static final String THOUSANDS = "mille";
	@Override
	public String translate(Number number) {
		validate(number);
		return translateNumber(number.longValue());
	}


	private String translateNumber(long number) {
		if (number == 0) {
			return UNITS[(int) number];
		}
		
		StringBuilder numToWord = new StringBuilder();
		int n = init(number);
		
		int word;
		long resto;
		long factor;
		
		while (number != 0) {
			switch(n) {
			case THOUSAND:
				factor = (long) Math.pow(10, THOUSAND);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				if (word > 1) {
					numToWord.append(translate(word)).append(" ");
				}
				
				numToWord.append(THOUSANDS);
				number = resto;
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				
				if (word > 1 && resto == 0) {
					numToWord.append("s");
				}
				n = init(resto);
				break;
				
			case HUNDRED:
				factor = (long) Math.pow(10, HUNDRED);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				if (word > 1) {
					numToWord.append(translate(word)).append(" ");
				}
				
				numToWord.append(HUNDREDS);
				number = resto;
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				
				if (word > 1 && resto == 0) {
					numToWord.append("s");
				}
				n = init(resto);
				break;
			case TEN:
				factor = (long) Math.pow(10, TEN);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				switch (word) {
				case 1:
					if (resto == 0) {
						numToWord.append(TENS[word]);
						number = 0;
					} else if(resto > 0) {
						numToWord.append(ESP[(int) resto]);
						number = 0;
					}
					
					resto = 0;
					break;

				case 7:
					numToWord.append(TENS[word]);
					if (resto == 1) {
						numToWord = new StringBuilder(numToWord.toString().replace("-dix", ""));
						numToWord.append(" et ");
						numToWord.append(translate(resto + 10));
					} else if (resto > 1) {
						numToWord = new StringBuilder(numToWord.toString().replace("dix", ""));
						numToWord.append(translate(resto + 10));
					}
					resto = 0;
					break;
				case 8:
					numToWord.append(TENS[word]);
					
					if (resto == 0) {
						numToWord.append("s");
					} else {
						numToWord.append("-");
						numToWord.append(translate(resto));
					}
					
					resto = 0;
					break;
				case 9:
					numToWord.append(TENS[word]);
					numToWord = new StringBuilder(numToWord.toString().replace("dix", ""));
					numToWord.append(translate(resto  + 10));
					
					resto = 0;
					break;
					
				default:
					numToWord.append(TENS[word]);
					
					if (resto > 1) {
						numToWord.append("-");
					} else if (resto == 1) {
						numToWord.append(" et ");
					}
					number = resto;
					n = UNIT;
					break;
				}

				number = resto;
				break;
				
			case UNIT:
				numToWord.append(UNITS[(int)number]);
				number = 0;
				break;
				
			default:
				return "No se puede convertir el numero";
			}
		}
		
		String str = numToWord.toString();
		if (str.endsWith(UNITS[1]) && !str.equalsIgnoreCase(UNITS[1])) {
			str = str.substring(0, str.length() -1);
		}
		
		return str;
	}
}