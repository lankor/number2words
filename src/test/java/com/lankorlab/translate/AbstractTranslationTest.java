package com.lankorlab.translate;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.number.TranslatorSpanishTest;


public abstract class AbstractTranslationTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractTranslationTest.class);
	
	protected String[] process(String line) {
		return line.split("=");
	}

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
