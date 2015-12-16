package com.lankorlab.translate;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.impl.EnglishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorEnglishTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslatorEnglishTest.class);
	
	private static List<String> test;
	
	private NumberTranslator english = new EnglishTranslator();
	
	@BeforeClass
	public static void loadFile() {
		File testFile;
		try {
			testFile = new File(String.valueOf(TranslatorSpanishTest.class
					.getClassLoader().getResource("english.txt").getPath()));
			test = FileUtils.readLines(testFile);
		} catch (IOException e) {
			LOGGER.warn("No se pudo cargar el archivo con los datos de prueba.");
		}
	}
	@Test
	public void translatorZero() {
		int i = 0;
		String value = english.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = english.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("zero", value);
	}
	
	@Test
	public void translator() {
		for (String line : test) {
			String[] lineArray = line.split("=");
			String value = english.translate(Long.parseLong(lineArray[0]));
			LOGGER.info(lineArray[0] + " " + value);
			Assert.assertEquals(lineArray[1], value);
		}
		
	}
}
