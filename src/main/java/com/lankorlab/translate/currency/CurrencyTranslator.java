package com.lankorlab.translate.currency;


public interface CurrencyTranslator {
	String translate(Number number, CurrencyType currency);
}
