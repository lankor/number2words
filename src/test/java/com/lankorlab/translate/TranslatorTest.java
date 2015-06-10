package com.lankorlab.translate;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.CurrencyTranslator;
import com.lankorlab.translate.currency.CurrencyType;
import com.lankorlab.translate.currency.impl.SpanishCurrencyTranslator;
import com.lankorlab.translate.impl.EnglishTranslator;
import com.lankorlab.translate.impl.SpanishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorTest {
	private static final Logger log = LoggerFactory.getLogger(TranslatorTest.class);
	private NumberTranslator spanish = new SpanishTranslator();
	private NumberTranslator english = new EnglishTranslator();
	
	@Test
	public void traductor() {
		//9,223,372,036,854,775,807
//		long index = Long.MAX_VALUE;
		long index = 1223372036854775807L;
//		long index = 9223372036854775L;
//		long index = 9223372036854L;
		for (int i = 0; i < 10; i++) {
			log.info(index + " " + spanish.translate(index--));
		}
	}
	
	@Test
	public void traductorIngles() {
		//9,223,372,036,854,775,807
		long index = 1223372036854775807L;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + english.translate(index--));
		}
	}
	
	@Ignore
	@Test
	public void traductorMoneda() {
		CurrencyTranslator trans = new SpanishCurrencyTranslator();
		
		long index = 0;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + trans.translate(index++, CurrencyType.MEXICO));
		}
	}
}
