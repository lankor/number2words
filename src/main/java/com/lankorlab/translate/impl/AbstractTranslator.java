package com.lankorlab.translate.impl;

public abstract class AbstractTranslator {

	/**
	 * Trillones
	 */
	protected static final int TRILLION = 18;
	
	/**
	 * Miles de Billon
	 */
	protected static final int THOUSAND_BILLION = 15;
	
	/**
	 * Billones
	 */
	protected static final int BILLION = 12;
	
	/**
	 * Miles de millon
	 */
	protected static final int THOUSAND_MILLION = 9;
	/**
	 * Millon
	 */
	protected static final int MILLION = 6;
	/**
	 * Mil
	 */
	protected static final int THOUSAND = 3;
	
	/**
	 * Centena
	 */
	protected static final int HUNDRED = 2;
	/**
	 * Decena
	 */
	protected static final int TEN = 1;
	/**
	 * Unidad
	 */
	protected static final int UNIT = 0;
	
	protected void validate(Number number) {
		if (number.longValue() < 0) {
			throw new IllegalArgumentException("El valor es incorecto, el " +
					"número debe ser mayor o igual a 0");
		}
	}
	
	/**
	 * Devuelve el rango de numeros al que pertenece el valor para poder 
	 * iniciar su converion en su representacion escrita.
	 * @param number Numero del que se quiere conocer su rango
	 * @return Rango al que pertenece el numero.
	 */
	protected int init(long number) {
		
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
			
		case 16:
		case 17:
		case 18:
			return THOUSAND_BILLION;
		
		case 19:
			return TRILLION;
			
		default:
			return 0;
		}
		
	}
	
}
