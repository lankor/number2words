package com.lankorlab.translate.impl;

/**
 * Implementacion al español de la representacion escrita de numeros.
 * 
 * @author Luis Ángel Cárdenas  luis.cardeno@gmail.com
 * @see Long
 * {@link Long#MAX_VALUE}
 *
 */
public class SpanishTranslator extends AbstractTranslator {

	/**
	 * Representacion escrita de las unidades.
	 */
	private static final String[] UNITS = {"cero", "uno", "dos", "tres", 
		"cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
	
	/**
	 * Representacion escrita de los valores especiales entre 11 y 29
	 */
	private static final String[] ESP = {null, "once", "doce", "trece", 
		"catorce", "quince", "dieciseis", "diecisiete", "dieciocho", 
		"diecinueve", null, "veintiuno", "veintidos", "veintitres", 
		"veinticuatro", "veinticinco", "veintiseis", "veintisiete", 
		"veintiocho", "veintinueve"};
	
	/**
	 * Representacion escrita de la decenas.
	 */
	private static final String[] TENS = {null, "diez", "veinte", "treinta", 
		"cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
	
	/**
	 * Representacion escrita de las centenas.
	 */
	private static final String[] HUNDREDS = {null, "cien", "doscientos", 
		"trescientos", "cuatrocientos", "quinientos", "seiscientos", 
		"setecientos", "ochocientos","novecientos"};
	/**
	 * Representacion escrita de millares
	 */
	private static final String THOUSANDS = "mil";
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] MILLIONS = {"millón", "millones"};
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] BILLIONS = {"billón", "billones"};
	
	/**
	 * Representacion escrita de millones tanto en singular como en plural.
	 */
	private static final String[] TRILLIONS = {"trillón", "trillones"};
	
	
	
	/**
	 * <p>
	 * Implementa el algoritmo de traduccion de numeros a texto en el idioma
	 * español
	 * </p>
	 * <p>
	 * El algoritmo conciste en obtener el tamaño del numero para determinar el
	 * rango que tiene (trillones, billones, millones, etc.) y traducir cada una
	 * de las partes del numero.
	 * </p>
	 * @param number Numero que se va a convertir en texto en español.
	 * @return El numero convertido en el texto correspondiente al idioma español.
	 */
	protected String translateNumber(long number) {
		if (number == 0) {
			return UNITS[(int) number];
		}
		
		StringBuilder numToWord = new StringBuilder();
		int range = init(number);
		
		Cantidad cant = null;
		
		while (number != 0) {
			switch(range) {
			case TRILLION:
				number = translate(number, numToWord, TRILLION, TRILLIONS);
				range = init(number);
				break;
				
			case THOUSAND_BILLION:
				number = translateThousands(number, numToWord, BILLION, BILLIONS);
				range = init(number);
				break;
			
			case BILLION:
				number = translate(number, numToWord, BILLION, BILLIONS);
				range = init(number);
				
				break;
				
			case THOUSAND_MILLION:
				number = translateThousands(number, numToWord, MILLION, MILLIONS);
				range = init(number);
				break;
				
			case MILLION:
				number = translate(number, numToWord, MILLION, MILLIONS);
				range = init(number);
				break;
				
			case THOUSAND:
				cant = new Cantidad(number, THOUSAND);
				
				numToWord.append(translateHundreds(cant.getNumber())).append(" ")
				.append(THOUSANDS);
				
				validarResto(numToWord, cant);
				
				number = cant.getRemainder();
				range = init(number);
				
				break;
				
			case HUNDRED:
				cant = new Cantidad(number, HUNDRED);
				
				numToWord.append(HUNDREDS[cant.getNumber()]);
				
				if (cant.getNumber() == 1 && cant.getRemainder() > 0) {
					numToWord.append("to ");
				} else
					validarResto(numToWord, cant);
				
				number = cant.getRemainder();
				range = init(number);
				break;
				
			case TEN:
				cant = new Cantidad(number, TEN);
				
				switch(cant.getNumber()) {
				case 1:
					
					if (cant.getRemainder() == 0) {
						numToWord.append(TENS[cant.getNumber()]);
					} else {
						numToWord.append(ESP[(int) cant.getRemainder()]);
					}
					
					number = 0;
					break;
					
				case 2:
					if (cant.getRemainder() == 0) {
						numToWord.append(TENS[cant.getNumber()]);
					} else {
						numToWord.append(ESP[(int)cant.getRemainder() + 10]);
					}
					
					number = 0;
					break;
					
				default:
					numToWord.append(TENS[cant.getNumber()]);
					
					if (cant.getRemainder() > 0) {
						numToWord.append(" y ");
					}
					number = cant.getRemainder();
					range = UNIT;
					break;
				}
				
				
				break;
				
			case UNIT:
				numToWord.append(UNITS[(int)number]);
				number = 0;
				break;
				
				default:
					return "No se puede convertir el number";
			}
		}
		
		return numToWord.toString();
	}

	/**
	 * Devuelve el valor restante de la cantidad a traducir de acuerdo a la 
	 * posicion (millon, billlon, etc.).
	 * 
	 * Este metodo se usa en la traduccion de millares
	 * 
	 * @param number Cantidad que se va a traducir
	 * @param numToWord Texto con las traducciones previas, en caso de tenerlas.
	 * @param factor Posicion en la que encuentra la cantidad a traducir.
	 * @param unidades Valor del subfijo de acuerdo al la posicion de la 
	 * cantidad a traducir
	 * @return Modulo de la cantidad y el factor.
	 */
	private long translateThousands(long number, StringBuilder numToWord,
			int factor, String[] unidades) {
		Cantidad cant;
		cant = new Cantidad(number, factor);
		
		numToWord.append(translateNumber(cant.getNumber())).append(" ");
		
		numToWord.append(unidades[1]);
		
		validarResto(numToWord, cant);
		return cant.getRemainder();
	}

	/**
	 * Devuelve el valor restante de la cantidad a traducir de acuerdo a la 
	 * posicion (millon, billlon, etc.)
	 * 
	 * @param number Cantidad que se va a traducir
	 * @param numToWord Texto con las traducciones previas, en caso de tenerlas.
	 * @param factor Posicion en la que encuentra la cantidad a traducir.
	 * @param unidades Valor del subfijo de acuerdo al la posicion de la 
	 * cantidad a traducir
	 * @return Modulo de la cantidad y el factor.
	 */
	private long translate(long number, StringBuilder numToWord, int factor,
			String[] unidades) {
		Cantidad cant;
		cant = new Cantidad(number, factor);
		
		numToWord.append(translateHundreds(cant.getNumber())).append(" ");
		
		if (cant.getNumber() == 1) {
			numToWord.append(unidades[0]);
		} else {
			numToWord.append(unidades[1]);
		}
		
		validarResto(numToWord, cant);
		return cant.getRemainder();
	}

	/**
	 * Verifica si el remainder de la cantidad traducida es diferente de 0,
	 * para realizar el ajuste en la cadena resultado. Ya que si el remainder es
	 * mayor que 0, hay que agregar un espacio que entecedera a la traduccion 
	 * del remainder.
	 * 
	 * @param numToWord resultado de las traducciones previas de la cantidad original.
	 * @param cant Contiene la informacion sobre la cantidad que se esta traduciendo.
	 */
	private void validarResto(StringBuilder numToWord, Cantidad cant) {
		if (cant.getRemainder() > 0) {
			numToWord.append(" ");
		}
	}
	
	/**
	 * Devuelve la traduccion de centenas, es usado como metodo auxiliar para 
	 * las centenas de millo, billon, etc.
	 * 
	 * @param number Numero que se desea traducir.
	 * @return Texto de las centenas traducidas.
	 */
	private String translateHundreds(int number) {
		String word = translateNumber(number).trim();
			
		if (word.endsWith("uno")) {
			word = word.substring(0, word.length() - 1);
		}
		return word;
	}
	
	class Cantidad {
		private int number;
		private long remainder;
		
		public Cantidad(long number, int f) {
			long factor = getFactor(f);
			
			this.number = (int) (number / factor);
			this.remainder = (long) number % factor;
		}
		private long getFactor(int factor) {
			return (long) Math.pow(10, factor);
		}
		
		public long getRemainder() {
			return remainder;
		}
		
		public int getNumber() {
			return number;
		}
	}
}
