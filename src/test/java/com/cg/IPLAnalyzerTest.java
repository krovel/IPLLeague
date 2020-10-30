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
	public void givenIPLBattingCSVWhenCalculatedBattingAverageShouldReturnExactAnswer() {
		assertEquals(101,iplLeagueAnalyser.loadCSVData(FILE_PATH));
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsTop3BattingAverages() throws Exception {
	
		List<IPLBattingCSV> topBattingAverage = iplLeagueAnalyser.getTopBattingAverages(FILE_PATH);
		assertEquals(83.2, topBattingAverage .get(0).getAverage(), 0.0);
		assertEquals(69.2, topBattingAverage .get(1).getAverage(), 0.0);
		assertEquals(56.66, topBattingAverage .get(2).getAverage(), 0.0);
	}
	@Test
	public void givenIPLBattingCSVCSVFileReturnsTop3StrikeRates() throws Exception {

		List<IPLBattingCSV> topStrikeRate = iplLeagueAnalyser.getTopStrikingRates(FILE_PATH);
		assertEquals(333.33, topStrikeRate.get(0).getSR(), 0.0);
		assertEquals(204.81, topStrikeRate.get(1).getSR(), 0.0);
		assertEquals(200.00, topStrikeRate.get(2).getSR(), 0.0);
	}
	@Test
	public void givenIplDataCSVFileReturnsCricketerWithMax6s() throws Exception {

		List<IPLBattingCSV> batmenWithMax6s = iplLeagueAnalyser.getTopBatmenWithMax6s(FILE_PATH);
		assertEquals("Andre Russell", batmenWithMax6s.get(0).getPlayer());
	}

	@Test
	public void givenIplDataCSVFileReturnsCricketerWithMax4s() throws Exception {

		List<IPLBattingCSV> batmenWithMax4s = iplLeagueAnalyser.getTopBatmenWithMax4s(FILE_PATH);
		assertEquals("Shikhar Dhawan", batmenWithMax4s.get(0).player);
	}
}