package com.lankorlab.traslate;

/**
 * Define el comportamiento para traducir a su representacion escrita el valor 
 * de numeros naturales.
 * 
 * @author Luis Ángel Cárdenas  luis.cardeno@gmail.com
 * @version 1.0 06/12/2012
 *
 */
public interface NumberTranslator {
	
	/**
	 * Convierte el numero natural indicado a su representacion escrita. El 
	 * numero debe ser mayor o igual a 0
	 * 
	 * @param number Numero que sera convertido en su representacion escrita 
	 * correspondiente. El valor debe estar entre 0 y 9,223,372,036,854,775,807
	 * @return Cadena de texto con el numero indicado convertido en palabras.
	 */
	String translate(Number number);
}
