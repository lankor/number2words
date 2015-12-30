package com.lankorlab.translate;

/**
 * Define el comportamiento para traducir a su representacion escrita el valor 
 * de numeros naturales.
 * 
 * @author Luis Ángel Cárdenas  luis.cardeno@gmail.com
 *
 */
public interface NumberTranslator {
	
	/**
	 * <p>
	 * Convierte el número natural indicado a su representacion escrita en 
	 * letras. El numero debe ser mayor o igual a 0.
	 * </p>
	 * <p>
	 * En caso de que el número sea menor a 0, se lanza una excepcion del tipo
	 * {@link IllegalArgumentException}
	 * </p>
	 * @param number Numero que sera convertido en su representacion escrita 
	 * correspondiente. El valor debe estar entre 0 y 9,223,372,036,854,775,807.
	 * Que es el valor máximo del tipo long {@link Long#MAX_VALUE}.
	 * 
	 * @return Cadena de texto con el numero indicado convertido en texto.
	 */
	String translate(Number number);
}
