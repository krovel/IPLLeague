package com.cg;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IPLAnalyzerTest {
	public String FILE_PATH="./Batting.csv";
	public IPLAnalyzer iplLeagueAnalyser;
	
	@Before
	public void setUp() throws IOException {
		iplLeagueAnalyser=new IPLAnalyzer();
		iplLeagueAnalyser.loadDataToList(FILE_PATH);
	}

	@Test
	public void givenIplDataWhenCalculatedBattingAverageShouldReturnExactAnswer() {
		assertEquals(101,iplLeagueAnalyser.loadCSVData(FILE_PATH));
	}
	
	@Test
	public void givenIplDataCSVFileReturnsTop3BattingAverages() throws Exception {
	
		List<IPLBattingCSV> topBattingAverage = iplLeagueAnalyser.getTopBattingAverages(FILE_PATH);
		assertEquals(83.2, topBattingAverage .get(0).getAverage(), 0.0);
		assertEquals(69.2, topBattingAverage .get(1).getAverage(), 0.0);
		assertEquals(56.66, topBattingAverage .get(2).getAverage(), 0.0);
	}
}