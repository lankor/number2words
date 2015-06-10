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
	private static final String[] TENS = {null, "diez", "venite", "treinta", 
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
	 * Billones
	 */
	private static final int BILLION = 12;
	
	/**
	 * Miles de millon
	 */
	private static final int THOUSAND_MILLION = 9;
	/**
	 * Millon
	 */
	private static final int MILLION = 6;
	/**
	 * Mil
	 */
	private static final int THOUSAND = 3;
	
	/**
	 * Centena
	 */
	private static final int HUNDRED = 2;
	/**
	 * Decena
	 */
	private static final int TEN = 1;
	/**
	 * Unidad
	 */
	private static final int UNIT = 0;
	
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
					} else {
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
			
		case 13:
		case 14:
		case 15:
			return BILLION;
			
		default:
			return 0;
		}
		
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
