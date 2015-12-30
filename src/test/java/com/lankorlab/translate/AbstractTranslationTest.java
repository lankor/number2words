package com.lankorlab.translate;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.number.TranslatorSpanishTest;

/**
 * Clase que define la funcionalidad comun para la bateria de pruebas.
 * 
 * @author Luis Ángel Cardenas luis.cardenas@gmail.com
 *
 */
public abstract class AbstractTranslationTest {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractTranslationTest.class);
	
	/**
	 * Descompone la linea en 2 partes: la parte numerica y su texto 
	 * correspondiente para poder llevar a cabo las validaciones.
	 * 
	 * <p>
	 * La estructura de las lineas del archivo es la siguiente:
	 * <pre>
	 * 		numero=numero expresado en texto
	 * </pre>
	 * 
	 * Por ejemplo:
	 * <pre>
	 * 		12389=doce mil trescientos ochenta y nueve
	 * </pre>
	 * 
	 * </p>
	 * @param line
	 * @return
	 */
	protected String[] process(String line) {
		return line.split("=");
	}

	/**
	 * Devuelve un <code>List</code> con todas lineas que contiene el archivo 
	 * que contiene los casos de prueba.
	 * 
	 * @param name Ruta del archivo
	 * @return Lineas leidas desde el archivo
	 */
	protected List<String> loadFile(String name) {
		File testFile;
		try {
			testFile = new File(String.valueOf(TranslatorSpanishTest.class
					.getClassLoader().getResource(name).getPath()));
			return FileUtils.readLines(testFile);
		} catch (IOException e) {
			LOGGER.warn("No se pudo cargar el archivo con los datos de prueba.");
		}
		
		return null;
	}
}
