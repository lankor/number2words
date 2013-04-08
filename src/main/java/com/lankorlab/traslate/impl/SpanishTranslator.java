package com.lankorlab.traslate.impl;

import com.lankorlab.traslate.NumberTranslator;

/**
 * Implementacion al español de la representacion escrita de numeros, el maximo
 * numero que se puede traducir es 999,999,999,999.
 * 
 * @author Luis Ángel Cárdenas  luis.cardeno@gmail.com
 * @version 1.0 06/12/2012
 *
 */
public class SpanishTranslator implements NumberTranslator {

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
	private static final String[] TENS = {null, "diez", "venite", "treinta", 
		"cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
	
	/**
	 * Representacion escrita de las centenas.
	 */
	private static final String[] HUNDREDS = {null, "cien", "doscientos", 
		"trecientos", "cuatrocientos", "quinientos", "seisientos", 
		"setecientos", "ochocientos","novecientos"};
	/**
	 * Representacion escrita de millares
	 */
	private static final String THOUSANDS = "mil";
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] MILLIONS = {null, "millon", "millones"};
	
	/**
	 * Miles de millon
	 */
	private static final int THOUSAND_MILLION = 1000000000;
	/**
	 * Millon
	 */
	private static final int MILLION = 1000000;
	/**
	 * Mil
	 */
	private static final int THOUSAND = 1000;
	
	/**
	 * Centena
	 */
	private static final int HUNDRED = 100;
	/**
	 * Decena
	 */
	private static final int TEN = 10;
	/**
	 * Unidad
	 */
	private static final int UNIT = 1;
	
	@Override
	public String translate(long number) {
		if (number < 0) {
			throw new IllegalArgumentException("El valor es incorecto, el " +
					"numero debe ser mayor o igual a 0");
		}
		return translateNumber(number);
	}

	private String translateNumber(long number) {
		if (number == 0) {
			return UNITS[(int) number];
		}
		
		StringBuilder numToWord = new StringBuilder();
		int n = init(number);
		
		int word;
		int resto;
		
		while (number != 0) {
			switch(n) {
			case THOUSAND_MILLION:
				word = (int)(number / MILLION);
				resto = (int)(number % MILLION);
				
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
				word = (int) (number / MILLION);
				resto = (int) (number % MILLION);
				
				if (word == 1) {
					numToWord.append(UNITS[word].substring(0, UNITS[word].length() -1));
					numToWord.append(" ");
					numToWord.append(MILLIONS[word]);
				} else if ( word > 1 && word < 10) {
					numToWord.append(UNITS[word]);
					numToWord.append(" ");
					numToWord.append(MILLIONS[2]);
				} else if (word > 9 && word < 1000){
					numToWord.append(translateNumber(word));
					if (!numToWord.toString().endsWith(" ")) {
						numToWord.append(" ");
					}
					numToWord.append(MILLIONS[2]);
				}
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				break;
				
			case THOUSAND:
				word = (int) (number / THOUSAND);
				resto = (int) (number % THOUSAND);
				
				if (word == 1) {
					numToWord.append(THOUSANDS);
				} else if ( word > 1 && word < 10) {
					numToWord.append(UNITS[word]);
					numToWord.append(" ");
					numToWord.append(THOUSANDS);
				} else if (word > 9 && word < 1000){
					String thousands = translateNumber(word).trim();
					if (thousands.endsWith("uno")) {
						thousands = thousands.substring(0, thousands.length() - 1);
					}
					numToWord.append(thousands);
					if (!numToWord.toString().endsWith(" ")) {
						numToWord.append(" ");
					}
					numToWord.append(THOUSANDS);
				}
				
				if (resto > 0) {
					numToWord.append(" ");
				}
				number = resto;
				n = init(resto);
				
				break;
				
			case HUNDRED:
				word = (int)number / HUNDRED;
				resto = (int)number % HUNDRED;
				
				if ( word > 0 && word < 10) {
					numToWord.append(HUNDREDS[word]);
					if (word == 1 && resto > 0) {
						numToWord.append("to ");
					} else {
						numToWord.append(" ");
					}
				}
				
				number = resto;
				n = init(resto);
				break;
				
			case TEN:
				word = (int)number / TEN;
				resto = (int)number % TEN;
				
				if (word == 1 && resto > 0) {
					numToWord.append(ESP[resto]);
					number = 0;
				} else if (word == 1 && resto == 0) {
					numToWord.append(TENS[word]);
					number = 0;
				} else if (word == 2 && resto > 0) {
					numToWord.append(ESP[resto + 10]);
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

	/**
	 * Devuelve el rango de numeros al que pertenece el valor para poder 
	 * iniciar su converion en su representacion escrita.
	 * @param number Numero del que se quiere conocer su rango
	 * @return Rango al que pertenece el numero.
	 */
	private int init(long number) {
		
		switch (String.valueOf(number).length()) {
		case 1:
			return UNIT;
			
		case 2:
			return TEN;

		case 3:
			return HUNDRED;
			
		case 4:
		case 5:
		case 6:
			return THOUSAND;
			
		case 7:
		case 8:
		case 9:
			return MILLION;
			
		case 10:
		case 11:
		case 12:
			return THOUSAND_MILLION;
			
		default:
			return 0;
		}
		
	}

	@Override
	public String translate(int number) {
		return translate((long) number);
	}

	@Override
	public String translate(short number) {
		return translate((long) number);
	}

	@Override
	public String translate(byte number) {
		return translate((long) number);
	}
}
