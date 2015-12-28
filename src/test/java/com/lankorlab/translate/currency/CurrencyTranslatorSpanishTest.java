package com.lankorlab.translate.currency;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.impl.SpanishCurrencyTranslator;

/**
 * Test del traductor en español con las diferentes monedas configuradas.
 * @author lankor
 *
 */
public class CurrencyTranslatorSpanishTest extends AbstractCurrencyTranslatorTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CurrencyTranslatorSpanishTest.class);
	
	/**
	 * traductor utilizado para esta prueba
	 */
	CurrencyTranslator currency = new SpanishCurrencyTranslator();
	
	/**
	 * Prueba para traducir el valor 0
	 */
	@Test
	public void translatorZero() {
		int i = 0;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero pesos 00/100 MXN", value);
	}
	
	/**
	 * Prueba para traducir un valor negativo
	 */
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		currency.translate(i, CurrencyType.MEXICO);
	}
	
	/**
	 * Prueba para traducir los valores tomados desde el archivo de texto con 
	 * moneda mexicana.
	 */
	@Test
	public void translatorMonedaMexico() {
		translate(currency, "currency/spanish_mexico.txt", CurrencyType.MEXICO);
	}

	/**
	 * Prueba para traducir los valores tomados desde el archivo de texto con 
	 * moneda de Estados Unidos.
	 */
	@Test
	public void translatorMonedaEstadosUnidos() {
		translate(currency, "currency/spanish_usa.txt", CurrencyType.USA);
	}
}
