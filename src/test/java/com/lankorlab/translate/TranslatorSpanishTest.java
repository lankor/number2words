package com.lankorlab.translate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.CurrencyTranslator;
import com.lankorlab.translate.currency.CurrencyType;
import com.lankorlab.translate.currency.impl.SpanishCurrencyTranslator;
import com.lankorlab.translate.impl.SpanishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorSpanishTest {
	private static final Logger log = LoggerFactory.getLogger(TranslatorSpanishTest.class);
	private NumberTranslator spanish = new SpanishTranslator();
	private CurrencyTranslator currency = new SpanishCurrencyTranslator();
	
	@Test
	public void traductor() {
		long index = Long.MAX_VALUE;
//		long index = 1223372036854775807L;
//		long index = 9223372036854775L;
//		long index = 9223372036854L;

		for (int i = 0; i < 10; i++) {
			log.info(index + " " + spanish.translate(index--));
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
