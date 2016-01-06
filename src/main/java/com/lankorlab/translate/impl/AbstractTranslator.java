package com.lankorlab.translate.impl;

import com.lankorlab.translate.NumberTranslator;

/**
 * Clase que abstrae la funcionalidad común del traductor de números a texto.
 * 
 * Las traducciones se pueden cotejar con las de la pagina:
 * <a href="http://www.ultralingua.com/onlinedictionary/numbers">http://www.ultralingua.com</a>
 * 
 * @author Luis Angel Cárdenas luis.cardeno@gmail.com
 *
 */
public abstract class AbstractTranslator implements NumberTranslator {

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
	
	@Override
	public String translate(Number number) {
		validate(number);
		return translateNumber(number.longValue());
	}
	
	/**
	 * Verifica que el numero que se quiere traducir sea un numero positivo 
	 * mayor o igual a 0, de no se asi se lanza una excepción.
	 * 
	 * @param number
	 * @throws IllegalArgumentException cuando el numero que recibe como 
	 * parámetro no es válido.
	 */
	protected final void validate(Number number) {
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
	protected final int init(long number) {
		
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
	
	/**
	 * Define el algoritmo empleado para la traduccion del numero a su 
	 * representacion en texto.
	 *
	 * @param number Numero que se va a convertir en texto.
	 * @return El numero convertido en el texto.
	 */
	protected abstract String translateNumber(long number);
}
