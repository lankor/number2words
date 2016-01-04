package com.lankorlab.translate.number;

import java.util.Random;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.NumberTranslator;
import com.lankorlab.translate.impl.FrenchTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FrenchTranslatorTest extends AbstractNumberTranslationTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FrenchTranslatorTest.class);
	
	private NumberTranslator french = new FrenchTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = french.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zéro", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		french.translate(i);
	}
	
	@Test
	public void translator() {
		translate(french, "number/french.txt");
	}
	
	@Test
	public void testTranslateNumber() {
		long i = 10673;
		String expected = "dix mille six cent soixante-treize";
		String value = french.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals(expected, value);
	}
	
}
