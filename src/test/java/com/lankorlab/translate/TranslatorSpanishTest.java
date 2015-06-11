package com.lankorlab.translate;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.impl.SpanishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorSpanishTest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslatorSpanishTest.class);
	
	private static final long[] TEST = {Long.MAX_VALUE, 9001249015728383882L,
		3281639321112968132L, 1257812030154818045L, 785375178029428624L,
		465262584395281916L, 786909893320680140L, 36839119722670043L,
		59237547369013342L, 12475604900459773L, 5348695497582325L,
		2313728491991341L, 1497256392841064L, 580844982473215L,
		247406925658438L, 129458982670520L, 95221298685196L, 73845315122596L,
		11205563246421L, 2480903258367L, 7438885154831L, 3128504740360L,
		537333663827L, 703709765318L, 165508342154L, 20770897866L, 57549304098L,
		16190088775L, 7498348169L, 6205418946L, 9944023443L, 463925028,
		578806999, 852736853, 65371632, 91787843, 55195973, 9749427, 5971547,
		1171678, 192525, 461023, 302583, 93067, 63449, 53875, 9842, 4261, 1254,
		153, 246, 938, 52, 27, 11, 3, 2, 8};
	
	private static final String[] TEST_ASSERT = {
		"nueve trillones doscientos veintitres mil trescientos setenta y dos billones treinta y seis mil ochocientos cincuenta y cuatro millones setecientos setenta y cinco mil ochocientos siete",
		"nueve trillones un mil doscientos cuarenta y nueve billones quince mil setecientos veintiocho millones trescientos ochenta y tres mil ochocientos ochenta y dos",
		"tres trillones doscientos ochenta y un mil seiscientos treinta y nueve billones trescientos veintiun mil ciento doce millones novecientos sesenta y ocho mil ciento treinta y dos",
		"un trillón doscientos cincuenta y siete mil ochocientos doce billones treinta mil ciento cincuenta y cuatro millones ochocientos dieciocho mil cuarenta y cinco",
		"setecientos ochenta y cinco mil trescientos setenta y cinco billones ciento setenta y ocho mil veintinueve millones cuatrocientos veintiocho mil seiscientos veinticuatro",
		"cuatrocientos sesenta y cinco mil doscientos sesenta y dos billones quinientos ochenta y cuatro mil trescientos noventa y cinco millones doscientos ochenta y un mil novecientos dieciseis",
		"setecientos ochenta y seis mil novecientos nueve billones ochocientos noventa y tres mil trescientos venite millones seiscientos ochenta mil ciento cuarenta",
		"treinta y seis mil ochocientos treinta y nueve billones ciento diecinueve mil setecientos veintidos millones seiscientos setenta mil cuarenta y tres",
		"cincuenta y nueve mil doscientos treinta y siete billones quinientos cuarenta y siete mil trescientos sesenta y nueve millones trece mil trescientos cuarenta y dos",
		"doce mil cuatrocientos setenta y cinco billones seiscientos cuatro mil novecientos millones cuatrocientos cincuenta y nueve mil setecientos setenta y tres",
		"cinco mil trescientos cuarenta y ocho billones seiscientos noventa y cinco mil cuatrocientos noventa y siete millones quinientos ochenta y dos mil trescientos veinticinco",
		"dos mil trescientos trece billones setecientos veintiocho mil cuatrocientos noventa y uno millones novecientos noventa y un mil trescientos cuarenta y uno",
		"un mil cuatrocientos noventa y siete billones doscientos cincuenta y seis mil trescientos noventa y dos millones ochocientos cuarenta y un mil sesenta y cuatro",
		"quinientos ochenta billones ochocientos cuarenta y cuatro mil novecientos ochenta y dos millones cuatrocientos setenta y tres mil doscientos quince",
		"doscientos cuarenta y siete billones cuatrocientos seis mil novecientos veinticinco millones seiscientos cincuenta y ocho mil cuatrocientos treinta y ocho",
		"ciento veintinueve billones cuatrocientos cincuenta y ocho mil novecientos ochenta y dos millones seiscientos setenta mil quinientos venite",
		"noventa y cinco billones doscientos veintiun mil doscientos noventa y ocho millones seiscientos ochenta y cinco mil ciento noventa y seis",
		"setenta y tres billones ochocientos cuarenta y cinco mil trescientos quince millones ciento veintidos mil quinientos noventa y seis",
		"once billones doscientos cinco mil quinientos sesenta y tres millones doscientos cuarenta y seis mil cuatrocientos veintiuno",
		"dos billones cuatrocientos ochenta mil novecientos tres millones doscientos cincuenta y ocho mil trescientos sesenta y siete",
		"siete billones cuatrocientos treinta y ocho mil ochocientos ochenta y cinco millones ciento cincuenta y cuatro mil ochocientos treinta y uno",
		"tres billones ciento veintiocho mil quinientos cuatro millones setecientos cuarenta mil trescientos sesenta",
		"quinientos treinta y siete mil trescientos treinta y tres millones seiscientos sesenta y tres mil ochocientos veintisiete",
		"setecientos tres mil setecientos nueve millones setecientos sesenta y cinco mil trescientos dieciocho",
		"ciento sesenta y cinco mil quinientos ocho millones trescientos cuarenta y dos mil ciento cincuenta y cuatro",
		"venite mil setecientos setenta millones ochocientos noventa y siete mil ochocientos sesenta y seis",
		"cincuenta y siete mil quinientos cuarenta y nueve millones trescientos cuatro mil noventa y ocho",
		"dieciseis mil ciento noventa millones ochenta y ocho mil setecientos setenta y cinco",
		"siete mil cuatrocientos noventa y ocho millones trescientos cuarenta y ocho mil ciento sesenta y nueve",
		"seis mil doscientos cinco millones cuatrocientos dieciocho mil novecientos cuarenta y seis",
		"nueve mil novecientos cuarenta y cuatro millones veintitres mil cuatrocientos cuarenta y tres",
		"cuatrocientos sesenta y tres millones novecientos veinticinco mil veintiocho",
		"quinientos setenta y ocho millones ochocientos seis mil novecientos noventa y nueve",
		"ochocientos cincuenta y dos millones setecientos treinta y seis mil ochocientos cincuenta y tres",
		"sesenta y cinco millones trescientos setenta y un mil seiscientos treinta y dos",
		"noventa y un millones setecientos ochenta y siete mil ochocientos cuarenta y tres",
		"cincuenta y cinco millones ciento noventa y cinco mil novecientos setenta y tres",
		"nueve millones setecientos cuarenta y nueve mil cuatrocientos veintisiete",
		"cinco millones novecientos setenta y un mil quinientos cuarenta y siete",
		"un millón ciento setenta y un mil seiscientos setenta y ocho",
		"ciento noventa y dos mil quinientos veinticinco",
		"cuatrocientos sesenta y un mil veintitres",
		"trescientos dos mil quinientos ochenta y tres",
		"noventa y tres mil sesenta y siete",
		"sesenta y tres mil cuatrocientos cuarenta y nueve",
		"cincuenta y tres mil ochocientos setenta y cinco",
		"nueve mil ochocientos cuarenta y dos",
		"cuatro mil doscientos sesenta y uno",
		"un mil doscientos cincuenta y cuatro",
		"ciento cincuenta y tres",
		"doscientos cuarenta y seis",
		"novecientos treinta y ocho",
		"cincuenta y dos",
		"veintisiete",
		"once",
		"tres",
		"dos",
		"ocho"
	};
	
	private NumberTranslator spanish = new SpanishTranslator();

	@Test
	public void translatorZero() {
		int i = 0;
		String value = spanish.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = spanish.translate(i);
		LOGGER.info(i + " " + value);
		Assert.assertEquals("cero", value);
	}
	
	@Test
	public void translator() {
		for (int i = 0; i < TEST.length; i++) {
			String value = spanish.translate(TEST[i]);
			LOGGER.info(TEST[i] + " " + value);
			Assert.assertEquals(TEST_ASSERT[i], value);
		}
		
	}
}
