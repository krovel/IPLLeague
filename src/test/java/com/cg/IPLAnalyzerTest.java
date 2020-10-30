package com.cg;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class IPLAnalyzerTest {
	public String FILE_PATH="./Batting.csv";
	public IPLAnalyzer IPLAnalyzer;
	public String FILE_PATH_BOWLING_DATA="./Bowling.csv";
	
	@Before
	public void setUp() throws IPLException {
		IPLAnalyzer=new IPLAnalyzer();
		IPLAnalyzer.loadDataToList(FILE_PATH);
		IPLAnalyzer.loadBowlingDataToList(FILE_PATH_BOWLING_DATA);
	}

	@Test
	public void givenIPLBattingCSV_loadData_ShouldReturnExactAnswer() {
		assertEquals(101,IPLAnalyzer.loadCSVData(FILE_PATH));
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsTop3BattingAverages(){
	
		List<IPLBattingCSV> topBattingAverage = IPLAnalyzer.getTopBattingAverages();
		assertEquals(83.2, topBattingAverage .get(0).getAverage(), 0.0);
		assertEquals(69.2, topBattingAverage .get(1).getAverage(), 0.0);
		assertEquals(56.66, topBattingAverage .get(2).getAverage(), 0.0);
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsTop3StrikeRates(){

		List<IPLBattingCSV> topStrikeRate = IPLAnalyzer.getTopStrikingRates();
		assertEquals(333.33, topStrikeRate.get(0).getSR(), 0.0);
		assertEquals(204.81, topStrikeRate.get(1).getSR(), 0.0);
		assertEquals(200.00, topStrikeRate.get(2).getSR(), 0.0);
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketerWithMax6s(){
		
		List<IPLBattingCSV> batmenWithMax6s = IPLAnalyzer.getTopBatmenWithMax6s();
		assertEquals("Andre Russell", batmenWithMax6s.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketerWithMax4s(){
		
		List<IPLBattingCSV> batmenWithMax4s = IPLAnalyzer.getTopBatmenWithMax4s();
		assertEquals("Shikhar Dhawan", batmenWithMax4s.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsBestStrikeRatesWith6sAnd4s(){
		List<IPLBattingCSV> listOfTopStrikeRate = IPLAnalyzer.getCricketerWithBestStrikingRateWith6sAnd4s();
	    assertEquals("Andre Russell", listOfTopStrikeRate.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketersWithGreatAverageAndBestStrikeRates(){
		List<IPLBattingCSV> listOfTopAverageWithBestStrikERate = IPLAnalyzer.getGreatestAverageWithBestStrikingRate();
		assertEquals("MS Dhoni", listOfTopAverageWithBestStrikERate.get(0).getPlayer());
	}
	
	@Test
	public void givenIPLBattingCSVCSVFileReturnsCricketersWithMaximumRunAndBestAverages(){
		List<IPLBattingCSV> listOfMaxRunAndGreatestAverage = IPLAnalyzer.getMaximumRunWithGreatestAverage();
		assertEquals("David Warner", listOfMaxRunAndGreatestAverage.get(0).getPlayer());
	}
	
	@Test
	public void givenIplBowlingData_SortBowlingDataAccordingToAverage_ReturnSameList() {
		List<IPLBowlingCSV> listOfTopBowlingAverage = IPLAnalyzer. getTopBowlingAverages();
		assertEquals("Krishnappa Gowtham",listOfTopBowlingAverage.get(0).player);
		assertEquals("Tim Southee",listOfTopBowlingAverage.get(1).player);
		assertEquals("Prasidh Krishna",listOfTopBowlingAverage.get(2).player);
	}
}