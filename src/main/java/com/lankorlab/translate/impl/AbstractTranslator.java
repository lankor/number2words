package com.lankorlab.translate.impl;

public class AbstractTranslator {
	protected void validate(Number number) {
		if (number.longValue() < 0) {
			throw new IllegalArgumentException("El valor es incorecto, el " +
					"número debe ser mayor o igual a 0");
		}
	}
}
