package com.lankorlab.translate.number;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.NumberTranslator;
import com.lankorlab.translate.impl.SpanishTranslator;

/**
 * <p>
 * Pruebas para el traductor de numeros a letras en idioma español.
 * </p>
 * 
 * @author Luis Ángel Cárdenas
 * 
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorSpanishTest extends AbstractNumberTranslationTest {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslatorSpanishTest.class);
	
	/**
	 * Implementacion del traductor que será sujeto a pruebas.
	 */
	private NumberTranslator spanish = new SpanishTranslator();
	
	/**
	 * Prueba para la traduccion del numero 0
	 */
	@Test
	public void translatorZero() {
		int i = 0;
		String value = spanish.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero", value);
	}
	
	/**
	 * Prueba la traduccion de un numero negativo.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		spanish.translate(i);
	}
	
	/**
	 * Prueba la traduccion de una serie de numeros, los cuales estan dentro de 
	 * un archivo de texto, con la finalidad de ir incrementando el numero de 
	 * pruebas sin necesidad de tocar el codigo existente.
	 */
	@Test
	public void translator() {
		translate(spanish, "number/spanish.txt");
	}
	
}
