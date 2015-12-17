package com.lankorlab.translate.number;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.AbstractTranslationTest;
import com.lankorlab.translate.NumberTranslator;


public abstract class AbstractNumberTranslationTest extends AbstractTranslationTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNumberTranslationTest.class);
	
	public AbstractNumberTranslationTest() {
		super();
	}
	
	protected void translate(NumberTranslator currency, String file) {
		for (String line : loadFile(file)) {
			String[] arrayLine = process(line);
			String value = currency.translate(Long.parseLong(arrayLine[0]));
			
			LOGGER.info(arrayLine[0] + " " + value);
			
			Assert.assertEquals(arrayLine[1], value);
		}
	}

}
