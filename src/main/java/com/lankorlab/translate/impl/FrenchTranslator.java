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
	
	private static final String[] HUNDREDS = {null, "cent", "cents"};

	private static final String THOUSANDS = "mille";
	
	private static final String[] MILLIONS = {null, "million", "millions"};
	
	private static final String[] THOUSAND_MILLIONS = {null, "milliard", "milliards"};
	
	private static final String[]BILLIONS = {null, "billion", "billions"};
	
	private static final String[] TRILLIONS = {null, "trillion", "trillions"};
	
	@Override
	public String translate(Number number) {
		validate(number);
		return translateNumber(number.longValue());
	}


	protected String translateNumber(long number) {
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
			case TRILLION:
				factor = (long) Math.pow(10, TRILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;

				String trillions = getHundreds(word);
				numToWord.append(trillions);
				
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				
				if (word == 1) {
					numToWord.append(TRILLIONS[word]);
				} else {
					numToWord.append(TRILLIONS[2]);
				}
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
				
			case THOUSAND_BILLION:
				factor = (long) Math.pow(10, THOUSAND_BILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				String thousandBillion = translateNumber(word);
				
				if (thousandBillion.equals("une")) {
					thousandBillion = "";
				} else if (word == 80){
					thousandBillion = thousandBillion.substring(0, thousandBillion.length() - 1);
				}
				
				numToWord.append(thousandBillion);
				
				
				if (!numToWord.toString().endsWith(" ") && !thousandBillion.isEmpty()) {
					numToWord.append(" ");
				}
				
				numToWord.append(THOUSANDS).append(" ").append(BILLIONS[2]);
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
				
			case BILLION:
				factor = (long) Math.pow(10, BILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				String billions = getHundreds(word);
				
				numToWord.append(billions);
				
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}

				if (word == 1) {
					numToWord.append(BILLIONS[word]);
				} else {
					numToWord.append(BILLIONS[2]);
				}
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				
				break;
				
			case THOUSAND_MILLION:
				factor = (long) Math.pow(10, THOUSAND_MILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				String thousandsMillion = getHundreds(word);
				numToWord.append(thousandsMillion);
				
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
								
				if (word == 1) {
					numToWord.append(THOUSAND_MILLIONS[word]);
				} else {
					numToWord.append(THOUSAND_MILLIONS[2]);
				}
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				
				break;
			case MILLION:
				factor = (long) Math.pow(10, MILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				String hundredMillion = getHundreds(word);
				numToWord.append(hundredMillion);
				
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
								
				if (word == 1) {
					numToWord.append(MILLIONS[word]);
				} else {
					numToWord.append(MILLIONS[2]);
				}
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				
				break;
				
			case THOUSAND:
				factor = (long) Math.pow(10, THOUSAND);
				word = (int) (number / factor);
				resto = (long) number % factor;
				String aux = null;
				
				if (word > 1) {
					if (word == 80) {
						aux = translate(word);
						
						numToWord.append(aux.substring(0, aux.length() - 1)).append(" ");
					} else {
						numToWord.append(translate(word)).append(" ");
					}
				}
				
				numToWord.append(THOUSANDS);
				number = resto;
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				
//				if (word > 1 && resto == 0) {
//					numToWord.append("s");
//				}
				n = init(resto);
				break;
				
			case HUNDRED:
				factor = (long) Math.pow(10, HUNDRED);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				if (word > 1) {
					numToWord.append(translate(word)).append(" ");
				}
				
				
				if (word > 1 && resto == 0) {
					numToWord.append(HUNDREDS[2]);
				} else {
					numToWord.append(HUNDREDS[1]);
				}
				
				number = resto;
				
				if (resto > 0) {
					numToWord.append(" ");
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
	
	private String getHundreds(int word) {
		String thousands = translateNumber(word).trim();
		
		if (word == 80) {
			
			thousands = thousands.substring(0, thousands.length() - 1);
		} 
		
		if (thousands.endsWith("une")) {
			thousands = thousands.substring(0, thousands.length() - 1);
		}
		return thousands;
	}
}