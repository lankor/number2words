package com.lankorlab.translate;

import java.util.Random;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lankorlab.translate.impl.EnglishTranslator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranslatorEnglishTest {
	private static final Logger log = LoggerFactory
			.getLogger(TranslatorEnglishTest.class);
	
	private static final long[] TEST = {Long.MAX_VALUE, 3093618405661292503L,
		1200589030892089304L, 908205538730045907L, 838903508978378930L,
		101028595060509783L, 65093677800524175L, 27609749697496285L,
		12480604746590384L, 9847636305982648L, 6690709733575006L,
		1500982813038847L, 657930485668400L, 504922725475905L,
		195489489034837L, 71076542078303L, 31221245487351L,
		18719199961744L, 9333215483735L, 6568002624457L, 3435294177396L,
		102843835916L, 505629777312L, 688523156652L, 80758894515L, 96914207455L,
		20119622768L, 8419413465L, 6573771730L, 1884531403, 149640351, 
		480117987, 107288541, 83258068, 14562832, 28915478, 2029835, 2295793,
		1329720, 842391, 426699, 588206, 45960, 36671, 21672, 7010, 3845,
		1144, 856, 536, 189, 79, 89, 52, 9, 3, 1};
	
	private static final String[] TEST_ASSERT = {
		"nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven",
		"three quintillion ninety-three quadrillion six hundred eighteen trillion four hundred five billion six hundred sixty-one million two hundred ninety-two thousand five hundred three",
		"one quintillion two hundred quadrillion five hundred eighty-nine trillion thirty billion eight hundred ninety-two million eighty-nine thousand three hundred four",
		"nine hundred eight quadrillion two hundred five trillion five hundred thirty-eight billion seven hundred thirty million forty-five thousand nine hundred seven", 
		"eight hundred thirty-eight quadrillion nine hundred three trillion five hundred eight billion nine hundred seventy-eight million three hundred seventy-eight thousand nine hundred thirty", 
		"one hundred one quadrillion twenty-eight trillion five hundred ninety-five billion sixty million five hundred nine thousand seven hundred eighty-three", 
		"sixty-five quadrillion ninety-three trillion six hundred seventy-seven billion eight hundred million five hundred twenty-four thousand one hundred seventy-five", 
		"twenty-seven quadrillion six hundred nine trillion seven hundred forty-nine billion six hundred ninety-seven million four hundred ninety-six thousand two hundred eighty-five", 
		"twelve quadrillion four hundred eighty trillion six hundred four billion seven hundred forty-six million five hundred ninety thousand three hundred eighty-four", 
		"nine quadrillion eight hundred forty-seven trillion six hundred thirty-six billion three hundred five million nine hundred eighty-two thousand six hundred forty-eight", 
		"six quadrillion six hundred ninety trillion seven hundred nine billion seven hundred thirty-three million five hundred seventy-five thousand six", 
		"one quadrillion five hundred trillion nine hundred eighty-two billion eight hundred thirteen million thirty-eight thousand eight hundred forty-seven", 
		"six hundred fifty-seven trillion nine hundred thirty billion four hundred eighty-five million six hundred sixty-eight thousand four hundred", 
		"five hundred four trillion nine hundred twenty-two billion seven hundred twenty-five million four hundred seventy-five thousand nine hundred five", 
		"one hundred ninety-five trillion four hundred eighty-nine billion four hundred eighty-nine million thirty-four thousand eight hundred thirty-seven", 
		"seventy-one trillion seventy-six billion five hundred forty-two million seventy-eight thousand three hundred three", 
		"thirty-one trillion two hundred twenty-one billion two hundred forty-five million four hundred eighty-seven thousand three hundred fifty-one", 
		"eighteen trillion seven hundred nineteen billion one hundred ninety-nine million nine hundred sixty-one thousand seven hundred forty-four", 
		"nine trillion three hundred thirty-three billion two hundred fifteen million four hundred eighty-three thousand seven hundred thirty-five", 
		"six trillion five hundred sixty-eight billion two million six hundred twenty-four thousand four hundred fifty-seven",
		"three trillion four hundred thirty-five billion two hundred ninety-four million one hundred seventy-seven thousand three hundred ninety-six", 
		"one hundred two billion eight hundred forty-three million eight hundred thirty-five thousand nine hundred sixteen", 
		"five hundred five billion six hundred twenty-nine million seven hundred seventy-seven thousand three hundred twelve", 
		"six hundred eighty-eight billion five hundred twenty-three million one hundred fifty-six thousand six hundred fifty-two", 
		"eighty billion seven hundred fifty-eight million eight hundred ninety-four thousand five hundred fifteen", 
		"ninety-six billion nine hundred fourteen million two hundred seven thousand four hundred fifty-five", 
		"twenty billion one hundred nineteen million six hundred twenty-two thousand seven hundred sixty-eight", 
		"eight billion four hundred nineteen million four hundred thirteen thousand four hundred sixty-five", 
		"six billion five hundred seventy-three million seven hundred seventy-one thousand seven hundred thirty", 
		"one billion eight hundred eighty-four million five hundred thirty-one thousand four hundred three", 
		"one hundred forty-nine million six hundred forty thousand three hundred fifty-one", 
		"four hundred eighty million one hundred seventeen thousand nine hundred eighty-seven", 
		"one hundred seven million two hundred eighty-eight thousand five hundred forty-one", 
		"eighty-three million two hundred fifty-eight thousand sixty-eight", 
		"fourteen million five hundred sixty-two thousand eight hundred thirty-two", 
		"twenty-eight million nine hundred fifteen thousand four hundred seventy-eight", 
		"two million twenty-nine thousand eight hundred thirty-five", 
		"two million two hundred ninety-five thousand seven hundred ninety-three",
		"one million three hundred twenty-nine thousand seven hundred twenty", 
		"eight hundred forty-two thousand three hundred ninety-one", 
		"four hundred twenty-six thousand six hundred ninety-nine", 
		"five hundred eighty-eight thousand two hundred six", 
		"forty-five thousand nine hundred sixty", 
		"thirty-six thousand six hundred seventy-one", 
		"twenty-one thousand six hundred seventy-two", 
		"seven thousand ten", 
		"three thousand eight hundred forty-five", 
		"one thousand one hundred forty-four", 
		"eight hundred fifty-six", 
		"five hundred thirty-six", 
		"one hundred eighty-nine", 
		"seventy-nine", 
		"eighty-nine", 
		"fifty-two", 
		"nine", 
		"three",
		"one"};
	
	private NumberTranslator english = new EnglishTranslator();
	
	@Test
	public void translatorZero() {
		int i = 0;
		String value = english.translate(i);
		log.info(i + " " + value);
		Assert.assertEquals("zero", value);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void translatorNegative() {
		int i = -1;
		String value = english.translate(i);
		log.info(i + " " + value);
		Assert.assertEquals("zero", value);
	}
	
	@Test
	public void translator() {
		for (int i = 0; i < TEST.length; i++) {
			String value = english.translate(TEST[i]);
			log.info(TEST[i] + " " + value);
			Assert.assertEquals(TEST_ASSERT[i], value);
		}
		
	}
	
	
	@Test
	public void generate() {
		Random r = new Random();
		
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < 100; i++) {
			str.append(r.nextLong());
		}
		
		log.info(str.toString());
	}
}
