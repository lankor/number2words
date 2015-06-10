package com.lankorlab.translate;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.CurrencyTranslator;
import com.lankorlab.translate.currency.CurrencyType;
import com.lankorlab.translate.currency.impl.EnglishCurrencyTranslator;
import com.lankorlab.translate.impl.EnglishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorEnglishTest {
	private static final Logger log = LoggerFactory.getLogger(TranslatorEnglishTest.class);
	private NumberTranslator english = new EnglishTranslator();
	private CurrencyTranslator currency = new EnglishCurrencyTranslator();
//	@Ignore
	@Test
	public void traductor() {
		long index = Long.MAX_VALUE;
//		long index = 1223372036854775807L;
//		long index = 9223372036854775L;
//		long index = 9223372036854L;

		for (int i = 0; i < 10; i++) {
			log.info(index + " " + english.translate(index--));
		}
	}
	
	@Test
	public void traductorMonedaMexico() {
		long index = 0;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + currency.translate(index++, CurrencyType.MEXICO));
		}
	}
	
	@Test
	public void traductorMonedaEstadosUnidos() {
		long index = 0;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + currency.translate(index++, CurrencyType.USA));
		}
	}
}
