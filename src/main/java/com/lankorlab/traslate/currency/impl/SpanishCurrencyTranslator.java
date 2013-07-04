package com.lankorlab.traslate.currency.impl;

import java.text.DecimalFormat;

import com.lankorlab.traslate.NumberTranslator;
import com.lankorlab.traslate.currency.CurrencyTranslator;
import com.lankorlab.traslate.currency.CurrencyType;
import com.lankorlab.traslate.impl.SpanishTranslator;

public class SpanishCurrencyTranslator implements CurrencyTranslator {
	NumberTranslator translator = new SpanishTranslator();
	
	@Override
	public String translate(Number number, CurrencyType currency) {
		long intPart = number.longValue();
		
		String decimalPart = getDecimalPart(number);
		String word = translator.translate(intPart);
		
		if (word.endsWith("uno")) {
			word = word.substring(0, word.length() - 1);
		}
		
		StringBuilder numToWord = new StringBuilder(word);
		
		if(number.longValue() == 1 && currency.equals(CurrencyType.MEXICO)) {
			numToWord.append(" peso");
		} else if (currency.equals(CurrencyType.MEXICO)) {
			numToWord.append(" pesos");
		}
		
		numToWord.append(" ");
		numToWord.append(decimalPart);
		numToWord.append("/100 ");
		numToWord.append(currency.getCode());
			
		return numToWord.toString();
	}

	private String getDecimalPart(Number number) {
		DecimalFormat format = new DecimalFormat("#0.00");
		String strNumber = format.format(number);
		String array[] = strNumber.split("\\.");
		
		return array[1];
	}
}
