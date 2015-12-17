package com.lankorlab.translate.currency;

import java.math.BigDecimal;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.AbstractTranslationTest;

public abstract class AbstractCurrencyTranslatorTest extends AbstractTranslationTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractCurrencyTranslatorTest.class);

	public AbstractCurrencyTranslatorTest() {
		super();
	}

	protected void translate(CurrencyTranslator currency, String file, CurrencyType currencyType) {
		for (String line : loadFile(file)) {
			String[] arrayLine = process(line);
			String value = currency.translate(new BigDecimal(arrayLine[0]), currencyType);
			
			LOGGER.info(arrayLine[0] + " " + value);
			
			Assert.assertEquals(arrayLine[1], value);
		}
	}

}