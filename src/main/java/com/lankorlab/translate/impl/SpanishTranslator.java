package com.lankorlab.translate.impl;

import com.lankorlab.translate.NumberTranslator;

/**
 * Implementacion al español de la representacion escrita de numeros, el maximo
 * numero que se puede traducir es 999,999,999,999.
 * 
 * @author Luis Ángel Cárdenas  luis.cardeno@gmail.com
 * @version 1.0 06/12/2012
 *
 */
public class SpanishTranslator extends AbstractTranslator implements NumberTranslator {

	/**
	 * Representacion escrita de las unidades.
	 */
	private static final String[] UNITS = {"cero", "uno", "dos", "tres", 
		"cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
	
	/**
	 * Representacion escrita de los valores especiales entre 11 y 29
	 */
	private static final String[] ESP = {null, "once", "doce", "trece", 
		"catorce", "quince", "dieciseis", "diecisiete", "dieciocho", 
		"diecinueve", null, "veintiuno", "veintidos", "veintitres", 
		"veinticuatro", "veinticinco", "veintiseis", "veintisiete", 
		"veintiocho", "veintinueve"};
	
	/**
	 * Representacion escrita de la decenas.
	 */
	private static final String[] TENS = {null, "diez", "veinte", "treinta", 
		"cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
	
	/**
	 * Representacion escrita de las centenas.
	 */
	private static final String[] HUNDREDS = {null, "cien", "doscientos", 
		"trescientos", "cuatrocientos", "quinientos", "seiscientos", 
		"setecientos", "ochocientos","novecientos"};
	/**
	 * Representacion escrita de millares
	 */
	private static final String THOUSANDS = "mil";
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] MILLIONS = {null, "millón", "millones"};
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] BILLIONS = {null, "billón", "billones"};
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] TRILLIONS = {null, "trillón", "trillones"};
	
	
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
				factor = (long) Math.pow(10, BILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				numToWord.append(translateNumber(word));
				
				
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				
				numToWord.append(BILLIONS[2]);
				
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
				factor = (long) Math.pow(10, MILLION);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				numToWord.append(translateNumber(word));
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				numToWord.append(MILLIONS[2]);
				
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
				
				String thousandsMillion = getHundreds(word);
				numToWord.append(thousandsMillion);
				
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
				
				String thousands = getHundreds(word);
				numToWord.append(thousands);
				
				if (!numToWord.toString().endsWith(" ")) {
					numToWord.append(" ");
				}
				numToWord.append(THOUSANDS);
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				
				number = resto;
				n = init(resto);
				
				break;
				
			case HUNDRED:
				factor = (long) Math.pow(10, HUNDRED);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				if ( word > 0 && word < 10) {
					numToWord.append(HUNDREDS[word]);
					if (word == 1 && resto > 0) {
						numToWord.append("to ");
					} else if (resto != 0) {
						numToWord.append(" ");
					}
				}
				
				number = resto;
				n = init(resto);
				break;
				
			case TEN:
				factor = (long) Math.pow(10, TEN);
				word = (int) (number / factor);
				resto = (long) number % factor;
				
				if (word == 1 && resto > 0) {
					numToWord.append(ESP[(int) resto]);
					number = 0;
				} else if (word == 1 && resto == 0) {
					numToWord.append(TENS[word]);
					number = 0;
				} else if (word == 2 && resto > 0) {
					numToWord.append(ESP[(int)resto + 10]);
					number = 0;
				} else if (word == 2 && resto == 0) {
					numToWord.append(TENS[word]);
					number = 0;
				} else if (word > 2) {
					numToWord.append(TENS[word]);
					if (resto > 0) {
						numToWord.append(" y ");
					}
					number = resto;
					n = UNIT;
				} 
				
				break;
				
			case UNIT:
				numToWord.append(UNITS[(int)number]);
				number = 0;
				break;
				
				default:
					return "No se puede convertir el numero";
			}
		}
		
		return numToWord.toString();
	}

	private String getHundreds(int word) {
		String thousands = translateNumber(word).trim();
			
		if (thousands.endsWith("uno")) {
			thousands = thousands.substring(0, thousands.length() - 1);
		}
		return thousands;
	}
}
