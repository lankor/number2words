package com.lankorlab.traslate.currency;

public enum CurrencyType {
	MEXICO("MXN", 484, "pesos", "peso")
	;
	
	String code;
	String singular;
	String plural;
	int numericCode;
	
	CurrencyType(String code, int numericCode, String plural, String singular) {
		this.code = code;
		this.numericCode = numericCode;
		this.plural = plural;
		this.singular = singular;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the numericCode
	 */
	public int getNumericCode() {
		return numericCode;
	}
	
	public String getPlural() {
		return plural;
	}
	
	public String getSingular() {
		return singular;
	}
}
