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
	
		List<IPLBattingCSV> topBattingAverage = iplLeagueAnalyser.getTopBattingAverages();
		assertEquals(83.2, topBattingAverage .get(0).getAverage(), 0.0);
		assertEquals(69.2, topBattingAverage .get(1).getAverage(), 0.0);
		assertEquals(56.66, topBattingAverage .get(2).getAverage(), 0.0);
	}
	@Test
	public void givenIPLBattingCSVCSVFileReturnsTop3StrikeRates() throws Exception {

		List<IPLBattingCSV> topStrikeRate = iplLeagueAnalyser.getTopStrikingRates();
		assertEquals(333.33, topStrikeRate.get(0).getSR(), 0.0);
		assertEquals(204.81, topStrikeRate.get(1).getSR(), 0.0);
		assertEquals(200.00, topStrikeRate.get(2).getSR(), 0.0);
	}
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketerWithMax6s() throws Exception {

		List<IPLBattingCSV> batmenWithMax6s = iplLeagueAnalyser.getTopBatmenWithMax6s();
		assertEquals("Andre Russell", batmenWithMax6s.get(0).getPlayer());
	}

	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketerWithMax4s() throws Exception {

		List<IPLBattingCSV> batmenWithMax4s = iplLeagueAnalyser.getTopBatmenWithMax4s();
		assertEquals("Shikhar Dhawan", batmenWithMax4s.get(0).player);
	}
	@Test
	public void givenIPLBattingCSVCSVFileReturnsBestStrikeRatesWith6sAnd4s() throws Exception {
		List<IPLBattingCSV> listOfTopStrikeRate = iplLeagueAnalyser.getCricketerWithBestStrikingRateWith6sAnd4s();
	    assertEquals("Andre Russell", listOfTopStrikeRate.get(0).getPlayer());
	}
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketersWithGreatAverageAndBestStrikeRates() throws Exception {
		List<IPLBattingCSV> listOfTopAverageWithBestStrikERate = iplLeagueAnalyser.getGreatestAverageWithBestStrikingRate();
		assertEquals("MS Dhoni", listOfTopAverageWithBestStrikERate.get(0).getPlayer());
	}
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketersWithMaximumRunAndBestAverages() throws Exception {
		List<IPLBattingCSV> listOfMaxRun = iplLeagueAnalyser
				.getMaximumRunWithGreatestAverage();
		assertEquals("David Warner", listOfMaxRun.get(0).getPlayer());
	}
}