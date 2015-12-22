package com.lankorlab.translate.currency.impl;

import com.lankorlab.translate.NumberTranslator;
import com.lankorlab.translate.currency.CurrencyTranslator;
import com.lankorlab.translate.currency.CurrencyType;
import com.lankorlab.translate.impl.SpanishTranslator;

public class SpanishCurrencyTranslator extends AbstractCurrencyTranslator implements CurrencyTranslator {
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
		
		numToWord.append(" ");
		
		switch (currency) {
		case MEXICO:
			if (number.longValue() == 1) {
				numToWord.append("peso");
			} else {
				numToWord.append("pesos");
			}
			break;
			
		case USA:
			if (number.longValue() == 1) {
				numToWord.append("dólar");
			} else {
				numToWord.append("dólares");
			}
			break;

		default:
			break;
		}
		
		numToWord.append(" ");
		numToWord.append(decimalPart);
		numToWord.append("/100 ");
		numToWord.append(currency.getCode());
			
		return numToWord.toString();
	}
}
