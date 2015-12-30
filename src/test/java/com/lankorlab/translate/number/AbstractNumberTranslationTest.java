package com.lankorlab.translate.number;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.AbstractTranslationTest;
import com.lankorlab.translate.NumberTranslator;

/**
 * Clase que define funcionalidad generica y auxiliar en la ejecucion de las 
 * pruebas del traductor.
 * 
 * @author Luis Ángel Cárdenas luis.cardeno@gmail.com
 *
 */
public abstract class AbstractNumberTranslationTest extends AbstractTranslationTest {
	/**
	 * Logger de la aplicacion.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractNumberTranslationTest.class);
	
	/**
	 * Constructor por default
	 */
	public AbstractNumberTranslationTest() {
		super();
	}
	
	/**
	 * Metodo auxiliar en la ejecucion de la bateria de pruebas, se encarga de la lectura del archivo del procesamiento de la linea del mimso y la verificacion de la traduccion.
	 * 
	 * @param trans Implementacion del traductor que se va a probar.
	 * @param file Ruta del archivo que contiene los casos de prueba.
	 */
	protected void translate(NumberTranslator trans, String file) {
		for (String line : loadFile(file)) {
			String[] arrayLine = process(line);
			String value = trans.translate(Long.parseLong(arrayLine[0]));
			
			LOGGER.info(arrayLine[0] + " " + value);
			
			Assert.assertEquals(arrayLine[1], value);
		}
	}

}
