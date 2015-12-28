package com.lankorlab.translate.currency;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.impl.EnglishCurrencyTranslator;

public class CurrencyTranslatorEnglishTest extends AbstractCurrencyTranslatorTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CurrencyTranslatorEnglishTest.class);
	
	private CurrencyTranslator currency = new EnglishCurrencyTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero pesos 00/100 MXN", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero pesos 00/100 MXN", value);
	}
	
	/**
	 * Prueba para traducir los valores tomados desde el archivo de texto con 
	 * moneda mexicana.
	 */
	@Test
	public void translatorMonedaMexico() {
		translate(currency, "currency/english_mexico.txt", CurrencyType.MEXICO);
	}

	/**
	 * Prueba para traducir los valores tomados desde el archivo de texto con 
	 * moneda de Estados Unidos.
	 */
	@Test
	public void translatorMonedaEstadosUnidos() {
		translate(currency, "currency/english_usa.txt", CurrencyType.USA);
	}
}
