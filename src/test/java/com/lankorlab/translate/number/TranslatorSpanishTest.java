package com.lankorlab.translate.number;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.NumberTranslator;
import com.lankorlab.translate.impl.SpanishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorSpanishTest extends AbstractNumberTranslationTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslatorSpanishTest.class);
	
	private NumberTranslator spanish = new SpanishTranslator();
	
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = spanish.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		spanish.translate(i);
	}
	
	@Test
	public void translator() {
		translate(spanish, "number/spanish.txt");
	}
}
