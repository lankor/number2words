package com.lankorlab.translate;

import java.util.Currency;
import java.util.Locale;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.traslate.NumberTranslator;
import com.lankorlab.traslate.currency.CurrencyTranslator;
import com.lankorlab.traslate.currency.CurrencyType;
import com.lankorlab.traslate.currency.impl.SpanishCurrencyTranslator;
import com.lankorlab.traslate.impl.SpanishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorTest {
	private static final Logger log = LoggerFactory.getLogger(TranslatorTest.class);
	
//	@Test
	public void traductor() {
		NumberTranslator trans = new SpanishTranslator();
		//9,223,372,036,854,775,807
		long index = 1999999990000l;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + trans.translate(index++));
		}
	}
	
	@Test
	public void traductorMoneda() {
		CurrencyTranslator trans = new SpanishCurrencyTranslator();
		
		long index = 0;
		for (int i = 0; i < 100; i++) {
			log.info(index + " " + trans.translate(index++, CurrencyType.MEXICO));
		}
	}
}
