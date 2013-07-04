package com.lankorlab.traslate.currency;


public interface CurrencyTranslator {
	String translate(Number number, CurrencyType currency);
}
