package com.lankorlab.translate.number;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.NumberTranslator;
import com.lankorlab.translate.impl.EnglishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorEnglishTest extends AbstractNumberTranslationTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslatorEnglishTest.class);
	
	private NumberTranslator english = new EnglishTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = english.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		english.translate(i);
	}
	
	@Test
	public void translator() {
		translate(english, "number/english.txt");
		
	}
}
