package com.lankorlab;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.lankorlab.traslate.NumberTranslator;
import com.lankorlab.traslate.currency.CurrencyTranslator;
import com.lankorlab.traslate.currency.CurrencyType;
import com.lankorlab.traslate.currency.impl.SpanishCurrencyTranslator;
import com.lankorlab.traslate.impl.SpanishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorTest {
	@Test
	public void traductor() {
		NumberTranslator trans = new SpanishTranslator();
		//9,223,372,036,854,775,807
		long index = 27;
		for (int i = 0; i < 10; i++) {
			System.out.println(index + " " + trans.translate(index++));
		}
	}
	
//	@Test
	public void traductorMoneda() {
		CurrencyTranslator trans = new SpanishCurrencyTranslator();
		
		for (long i = 0; i < 2000; i++) {
			System.out.println(i + " " + trans.translate(i, CurrencyType.MEXICO, new SpanishTranslator()));
//			Number number = (Math.random() * 1000000);
//			System.out.println(number + " " + trans.translate(number));
		}
	}
}
