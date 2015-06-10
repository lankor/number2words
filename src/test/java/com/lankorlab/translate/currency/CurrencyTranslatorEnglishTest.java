package com.lankorlab.translate.currency;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.impl.EnglishCurrencyTranslator;

public class CurrencyTranslatorEnglishTest {
	private static final Logger log = LoggerFactory
			.getLogger(CurrencyTranslatorEnglishTest.class);
	private CurrencyTranslator currency = new EnglishCurrencyTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = currency.translate(i, CurrencyType.MEXICO);
		log.info(i + " " + value);
		Assert.assertEquals("zero pesos 00/100 MXN", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = currency.translate(i, CurrencyType.MEXICO);
		log.info(i + " " + value);
		Assert.assertEquals("zero pesos 00/100 MXN", value);
	}
	
	@Test
	public void translatorMonedaMexico() {
		long index = 0;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + currency.translate(index++, CurrencyType.MEXICO));
		}
	}
	
	@Test
	public void translatorMonedaEstadosUnidos() {
		long index = 0;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + currency.translate(index++, CurrencyType.USA));
		}
	}
}
