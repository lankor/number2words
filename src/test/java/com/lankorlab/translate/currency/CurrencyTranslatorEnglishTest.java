package com.lankorlab.translate.currency;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.impl.EnglishCurrencyTranslator;

public class CurrencyTranslatorEnglishTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CurrencyTranslatorEnglishTest.class);
	
	private static double[] CURRENCY = {609459034.59, 341880638.96, 
		249618978.12, 27815738.85, 44896390.83, 25763296.02, 8349086.71,
		5292545.60, 8939273.88, 320921.73, 706966.48, 373838.71, 34803.89,
		63648.88, 76608.41, 4676.77, 6833.73, 7862.10, 159.70, 864.48, 235.64,
		41.31, 18.61, 51.70, 5.34, 9.07, 8.87};
	
	private static String[] CURRENCY_MEXICO_ASSERT = {
		"six hundred nine million four hundred fifty-nine thousand thirty-four pesos 59/100 MXN",
		"three hundred forty-one million eight hundred eighty thousand six hundred thirty-eight pesos 96/100 MXN",
		"two hundred forty-nine million six hundred eighteen thousand nine hundred seventy-eight pesos 12/100 MXN",
		"twenty-seven million eight hundred fifteen thousand seven hundred thirty-eight pesos 85/100 MXN",
		"forty-four million eight hundred ninety-six thousand three hundred ninety pesos 83/100 MXN",
		"twenty-five million seven hundred sixty-three thousand two hundred ninety-six pesos 02/100 MXN",
		"eight million three hundred forty-nine thousand eighty-six pesos 71/100 MXN",
		"five million two hundred ninety-two thousand five hundred forty-five pesos 60/100 MXN",
		"eight million nine hundred thirty-nine thousand two hundred seventy-three pesos 88/100 MXN",
		"three hundred twenty thousand nine hundred twenty-one pesos 73/100 MXN",
		"seven hundred six thousand nine hundred sixty-six pesos 48/100 MXN",
		"three hundred seventy-three thousand eight hundred thirty-eight pesos 71/100 MXN",
		"thirty-four thousand eight hundred three pesos 89/100 MXN",
		"sixty-three thousand six hundred forty-eight pesos 88/100 MXN",
		"seventy-six thousand six hundred eight pesos 41/100 MXN",
		"four thousand six hundred seventy-six pesos 77/100 MXN",
		"six thousand eight hundred thirty-three pesos 73/100 MXN",
		"seven thousand eight hundred sixty-two pesos 10/100 MXN",
		"one hundred fifty-nine pesos 70/100 MXN",
		"eight hundred sixty-four pesos 48/100 MXN",
		"two hundred thirty-five pesos 64/100 MXN",
		"forty-one pesos 31/100 MXN",
		"eighteen pesos 61/100 MXN",
		"fifty-one pesos 70/100 MXN",
		"five pesos 34/100 MXN",
		"nine pesos 07/100 MXN",
		"eight pesos 87/100 MXN",
	};
	
	private static String[] CURRENCY_USA_ASSERT = {
		"six hundred nine million four hundred fifty-nine thousand thirty-four dollars 59/100 USD",
		"three hundred forty-one million eight hundred eighty thousand six hundred thirty-eight dollars 96/100 USD",
		"two hundred forty-nine million six hundred eighteen thousand nine hundred seventy-eight dollars 12/100 USD",
		"twenty-seven million eight hundred fifteen thousand seven hundred thirty-eight dollars 85/100 USD",
		"forty-four million eight hundred ninety-six thousand three hundred ninety dollars 83/100 USD",
		"twenty-five million seven hundred sixty-three thousand two hundred ninety-six dollars 02/100 USD",
		"eight million three hundred forty-nine thousand eighty-six dollars 71/100 USD",
		"five million two hundred ninety-two thousand five hundred forty-five dollars 60/100 USD",
		"eight million nine hundred thirty-nine thousand two hundred seventy-three dollars 88/100 USD",
		"three hundred twenty thousand nine hundred twenty-one dollars 73/100 USD",
		"seven hundred six thousand nine hundred sixty-six dollars 48/100 USD",
		"three hundred seventy-three thousand eight hundred thirty-eight dollars 71/100 USD",
		"thirty-four thousand eight hundred three dollars 89/100 USD",
		"sixty-three thousand six hundred forty-eight dollars 88/100 USD",
		"seventy-six thousand six hundred eight dollars 41/100 USD",
		"four thousand six hundred seventy-six dollars 77/100 USD",
		"six thousand eight hundred thirty-three dollars 73/100 USD",
		"seven thousand eight hundred sixty-two dollars 10/100 USD",
		"one hundred fifty-nine dollars 70/100 USD",
		"eight hundred sixty-four dollars 48/100 USD",
		"two hundred thirty-five dollars 64/100 USD",
		"forty-one dollars 31/100 USD",
		"eighteen dollars 61/100 USD",
		"fifty-one dollars 70/100 USD",
		"five dollars 34/100 USD",
		"nine dollars 07/100 USD",
		"eight dollars 87/100 USD"
	};
	
	private CurrencyTranslator currency = new EnglishCurrencyTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero pesos 00/100 MXN", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero pesos 00/100 MXN", value);
	}
	
	@Test
	public void translatorMonedaMexico() {
		for (int i = 0; i < CURRENCY.length; i++) {
			String value = currency.translate(CURRENCY[i], CurrencyType.MEXICO);
			
			LOGGER.info(CURRENCY[i] + " " + value);
			
			Assert.assertEquals(CURRENCY_MEXICO_ASSERT[i], value);
		}
	}
	
	@Test
	public void translatorMonedaEstadosUnidos() {
		for (int i = 0; i < CURRENCY.length; i++) {
			String value = currency.translate(CURRENCY[i], CurrencyType.USA);
			
			LOGGER.info(CURRENCY[i] + " " + value);
			
			Assert.assertEquals(CURRENCY_USA_ASSERT[i], value);
		}
	}
}
