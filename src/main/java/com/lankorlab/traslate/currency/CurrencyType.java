package com.lankorlab.traslate.currency;

public enum CurrencyType {
	MEXICO("MXN", 484)
	;
	
	String code;
	int numericCode;
	
	CurrencyType(String code, int numericCode) {
		this.code = code;
		this.numericCode = numericCode;
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
}
