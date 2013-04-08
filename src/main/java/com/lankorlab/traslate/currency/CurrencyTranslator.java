package com.lankorlab.traslate.currency;

import com.lankorlab.traslate.NumberTranslator;

public interface CurrencyTranslator {
	String translate(Number number, CurrencyType currency, NumberTranslator translator);
}
