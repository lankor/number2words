package com.lankorlab.translate.currency.impl;

import java.text.DecimalFormat;

import com.lankorlab.translate.currency.CurrencyTranslator;

public abstract class AbstractCurrencyTranslator implements CurrencyTranslator {
	protected final long getIntegerPart(Number number) {
		return number.longValue();
	}
	
	protected final String getDecimalPart(Number number) {
		DecimalFormat format = new DecimalFormat("#########0.00");
		String strNumber = format.format(number);
		String array[] = strNumber.split("\\.");
		
		return array[1];
	}
}
