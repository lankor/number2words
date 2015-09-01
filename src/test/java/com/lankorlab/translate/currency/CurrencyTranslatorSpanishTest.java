package com.lankorlab.translate.currency;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.currency.impl.SpanishCurrencyTranslator;

public class CurrencyTranslatorSpanishTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CurrencyTranslatorSpanishTest.class);
	
	private static final double[] CURRENCY = {
		762307208.69, 465287388.64, 335258293.23, 55856187.64, 43866603.96,
		97328585.12, 4087594.80, 1868170.13, 2673968.31, 897862.64, 989928.07,
		388073.08, 56436.62, 81030.73, 14057.86, 9498.17, 1928.48, 4477.71,
		149.76, 834.58, 538.04, 96.55, 22.50, 25.34, 0.93, 6.40, 7.73
	};
	
	private static final String[] CURRENCY_MEXICO_ASSERT = {
		"setecientos sesenta y dos millones trescientos siete mil doscientos ocho pesos 69/100 MXN",
		"cuatrocientos sesenta y cinco millones doscientos ochenta y siete mil trescientos ochenta y ocho pesos 64/100 MXN",
		"trescientos treinta y cinco millones doscientos cincuenta y ocho mil doscientos noventa y tres pesos 23/100 MXN",
		"cincuenta y cinco millones ochocientos cincuenta y seis mil ciento ochenta y siete pesos 64/100 MXN",
		"cuarenta y tres millones ochocientos sesenta y seis mil seiscientos tres pesos 96/100 MXN",
		"noventa y siete millones trescientos veintiocho mil quinientos ochenta y cinco pesos 12/100 MXN",
		"cuatro millones ochenta y siete mil quinientos noventa y cuatro pesos 80/100 MXN",
		"un millón ochocientos sesenta y ocho mil ciento setenta pesos 13/100 MXN",
		"dos millones seiscientos setenta y tres mil novecientos sesenta y ocho pesos 31/100 MXN",
		"ochocientos noventa y siete mil ochocientos sesenta y dos pesos 64/100 MXN",
		"novecientos ochenta y nueve mil novecientos veintiocho pesos 07/100 MXN",
		"trescientos ochenta y ocho mil setenta y tres pesos 08/100 MXN",
		"cincuenta y seis mil cuatrocientos treinta y seis pesos 62/100 MXN",
		"ochenta y un mil treinta pesos 73/100 MXN",
		"catorce mil cincuenta y siete pesos 86/100 MXN",
		"nueve mil cuatrocientos noventa y ocho pesos 17/100 MXN",
		"un mil novecientos veintiocho pesos 48/100 MXN",
		"cuatro mil cuatrocientos setenta y siete pesos 71/100 MXN",
		"ciento cuarenta y nueve pesos 76/100 MXN",
		"ochocientos treinta y cuatro pesos 58/100 MXN",
		"quinientos treinta y ocho pesos 04/100 MXN",
		"noventa y seis pesos 55/100 MXN",
		"veintidos pesos 50/100 MXN",
		"veinticinco pesos 34/100 MXN",
		"cero pesos 93/100 MXN",
		"seis pesos 40/100 MXN",
		"siete pesos 73/100 MXN"
	};

	private static final String[] CURRENCY_USA_ASSERT = {
		"setecientos sesenta y dos millones trescientos siete mil doscientos ocho dólares 69/100 USD",
		"cuatrocientos sesenta y cinco millones doscientos ochenta y siete mil trescientos ochenta y ocho dólares 64/100 USD",
		"trescientos treinta y cinco millones doscientos cincuenta y ocho mil doscientos noventa y tres dólares 23/100 USD",
		"cincuenta y cinco millones ochocientos cincuenta y seis mil ciento ochenta y siete dólares 64/100 USD",
		"cuarenta y tres millones ochocientos sesenta y seis mil seiscientos tres dólares 96/100 USD",
		"noventa y siete millones trescientos veintiocho mil quinientos ochenta y cinco dólares 12/100 USD",
		"cuatro millones ochenta y siete mil quinientos noventa y cuatro dólares 80/100 USD",
		"un millón ochocientos sesenta y ocho mil ciento setenta dólares 13/100 USD",
		"dos millones seiscientos setenta y tres mil novecientos sesenta y ocho dólares 31/100 USD",
		"ochocientos noventa y siete mil ochocientos sesenta y dos dólares 64/100 USD",
		"novecientos ochenta y nueve mil novecientos veintiocho dólares 07/100 USD",
		"trescientos ochenta y ocho mil setenta y tres dólares 08/100 USD",
		"cincuenta y seis mil cuatrocientos treinta y seis dólares 62/100 USD",
		"ochenta y un mil treinta dólares 73/100 USD",
		"catorce mil cincuenta y siete dólares 86/100 USD",
		"nueve mil cuatrocientos noventa y ocho dólares 17/100 USD",
		"un mil novecientos veintiocho dólares 48/100 USD",
		"cuatro mil cuatrocientos setenta y siete dólares 71/100 USD",
		"ciento cuarenta y nueve dólares 76/100 USD",
		"ochocientos treinta y cuatro dólares 58/100 USD",
		"quinientos treinta y ocho dólares 04/100 USD",
		"noventa y seis dólares 55/100 USD",
		"veintidos dólares 50/100 USD",
		"veinticinco dólares 34/100 USD",
		"cero dólares 93/100 USD",
		"seis dólares 40/100 USD",
		"siete dólares 73/100 USD",
	};

	private CurrencyTranslator currency = new SpanishCurrencyTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero pesos 00/100 MXN", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = currency.translate(i, CurrencyType.MEXICO);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero pesos 00/100 MXN", value);
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
