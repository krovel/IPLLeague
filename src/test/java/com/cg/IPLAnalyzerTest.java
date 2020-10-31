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
		IPLAnalyzer.loadIPLBowlingCSVToList(FILE_PATH_BOWLING_DATA);
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
	public void givenIplIPLBowlingCSV_SortIPLBowlingCSVAccordingToAverage_ReturnSameList() {
		List<IPLBowlingCSV> listOfTopBowlingAverage = IPLAnalyzer. getTopBowlingAverages();
		assertEquals("Anukul Roy",listOfTopBowlingAverage.get(0).player);
		assertEquals("Jagadeesha Suchith",listOfTopBowlingAverage.get(1).player);
		assertEquals("Alzarri Joseph",listOfTopBowlingAverage.get(2).player);
	}


	@Test
	public void givenIplIPLBowlingCSV_SortIPLBowlingCSVAccordingToStrikeRate_ReturnSameList() {
		List<IPLBowlingCSV> listOfTopBowlingStrikeRate = IPLAnalyzer.getTopBowlingStrikeRates();
		assertEquals("Alzarri Joseph",listOfTopBowlingStrikeRate.get(0).player);
     	assertEquals("Ish Sodhi",listOfTopBowlingStrikeRate.get(1).player);
		assertEquals("Khaleel Ahmed",listOfTopBowlingStrikeRate.get(2).player);
	}
	
	@Test
	public void givenIplIPLBowlingCSV_SortIPLBowlingCSVAccordingToEconomy_ReturnSameList() {
		List<IPLBowlingCSV> listOfTopBowlersWithBestEconomy = IPLAnalyzer.getTopBowlerWithBestEconomy();
		assertEquals("Shivam Dube",listOfTopBowlersWithBestEconomy.get(0).player);
	}
	
	@Test
	public void givenIplIPLBowlingCSV_SortIPLBowlingCSVAccordingToBestEconomyWithMax4wAnd5w_ReturnSameList() {
		List<IPLBowlingCSV> listOfTopBowlersWithBestStrikeRateANdMax4w5w = IPLAnalyzer.getBowlersWithBestStrikeRateWithMax4wAnd5w();
		assertEquals("Kagiso Rabada",listOfTopBowlersWithBestStrikeRateANdMax4w5w.get(0).player);
	}
	
	@Test
	public void givenIplIPLBowlingCSV_SortIPLBowlingCSVAccordingToBestStrikeRateAndAverage_ReturnSameList() {
		List<IPLBowlingCSV> listOfTopBowlersWithBestStrikeRateAndAverage = IPLAnalyzer.getBowlersWithStrikeRateAndBestAverage();
    	assertEquals("Anukul Roy",listOfTopBowlersWithBestStrikeRateAndAverage.get(0).player);
	}
	
	@Test
	public void givenIplIPLBowlingCSV_SortWithMaxWicketsAndGreatAverage_ReturnSameList() {
		List<IPLBowlingCSV> listOfBowlersWithMaximumWicketsAndGreatAverage=IPLAnalyzer.getBowlersWithMaxWicketsAndBestAverage();
		assertEquals("Imran Tahir",listOfBowlersWithMaximumWicketsAndGreatAverage.get(0).player);
	}
	
	@Test
	public void givenBattingAndBowlingData_whenSortedOnBestBattingAndBowlingAverage_ShouldReturnCorrectList() {
		List<String> playersWithBestBattingAndBowlingAverage=IPLAnalyzer.getBestBattingAndBowlingAverage();
		assertEquals("Andre Russell", playersWithBestBattingAndBowlingAverage.get(0));
	    assertEquals("Marcus Stoinis", playersWithBestBattingAndBowlingAverage.get(1));

	}
}