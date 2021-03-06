package com.cg;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.util.*;

import opencsv.*;


public class IPLAnalyzer {
	
	public static List<IPLBattingCSV> IPLBattingCSVList;
	public static List<IPLBowlingCSV> IPLBowlingCSVList;
	
	public int loadCSVData(String csvFile) {
		int numOfEntries=0;
		try {
			
			Reader reader=Files.newBufferedReader(Paths.get(csvFile));
			Iterator<IPLBattingCSV> iterator=new OpenCSVBuilder().getCSVFileIterator(reader,IPLBattingCSV.class);
			Iterable<IPLBattingCSV> csvIterable = () -> iterator;
			numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return numOfEntries;
	}
	
	public void loadDataToList(String csvFile) throws IPLException {
		try {
			Reader reader=Files.newBufferedReader(Paths.get(csvFile));
		    IPLBattingCSVList = new CsvToBeanBuilder(reader).withType(IPLBattingCSV.class).build().parse();
		}
		catch(IOException e) {
			throw new IPLException("File path is incorrect",IPLException.ExceptionType.FILE_INCORRECT);
		}
	}
	
	public void loadIPLBowlingCSVToList(String csvFile)throws IPLException{
		try {
			Reader reader=Files.newBufferedReader(Paths.get(csvFile));
		    IPLBowlingCSVList = new CsvToBeanBuilder(reader).withType(IPLBowlingCSV.class).build().parse();
		}
		catch(IOException e) {
			throw new IPLException("File path is incorrect",IPLException.ExceptionType.FILE_INCORRECT);
		}
	}
	
	public List<IPLBattingCSV> getTopBattingAverages(){
		List<IPLBattingCSV> sortedAvgList = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getAverage(), player2.getAverage()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}
	
	public List<IPLBowlingCSV> getTopBowlingAverages(){
		List<IPLBowlingCSV> sortedAvgBowlingList = IPLBowlingCSVList.stream()
				.filter(player->player.avg!=0)
				.sorted((player1, player2) -> Double.compare(player1.avg, player2.avg))
				.collect(Collectors.toList());
		return sortedAvgBowlingList;
	}
	
	public List<IPLBattingCSV> getTopStrikingRates() {
		List<IPLBattingCSV> sortedStrikingRateList = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getSR(), player2.getSR()))
				.collect(Collectors.toList());
		Collections.reverse(sortedStrikingRateList);
		return sortedStrikingRateList;
	}
	
	public List<IPLBowlingCSV> getTopBowlingStrikeRates(){
		List<IPLBowlingCSV> sortedBowlingStrikingRateList = IPLBowlingCSVList.stream()
				.filter(player->player.sr!=0)
				.sorted((player1, player2) -> Double.compare(player1.sr, player2.sr))
				.collect(Collectors.toList());
		return sortedBowlingStrikingRateList;
	}
	public List<IPLBowlingCSV> getTopBowlerWithBestEconomy(){
		List<IPLBowlingCSV> sortedBowlerWithBestEconomy = IPLBowlingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.econ, player2.econ))
				.collect(Collectors.toList());
		return sortedBowlerWithBestEconomy;
	}
	
	public List<IPLBowlingCSV> getBowlersWithBestStrikeRateWithMax4wAnd5w(){
		int max4wAnd5w=IPLBowlingCSVList.stream()
				.map(player->player.num4w+player.num5w)
				.max(Integer::compare)
				.get();
		List<IPLBowlingCSV> bowlersWithMax4wAnd5w=IPLBowlingCSVList.stream()
				.filter(player->player.num4w+player.num5w==max4wAnd5w)
				.collect(Collectors.toList());
		List<IPLBowlingCSV> sortedWithBestStrikeRateAndMax4wAnd5w=bowlersWithMax4wAnd5w.stream()
				.sorted((player1,player2)->Double.compare(player1.sr, player2.sr))
				.collect(Collectors.toList());

	   return sortedWithBestStrikeRateAndMax4wAnd5w;
	}
	
	public List<IPLBowlingCSV> getBowlersWithStrikeRateAndBestAverage(){
		List<IPLBowlingCSV> sortedStrikeRateAndAverageList = IPLBowlingCSVList.stream()
				.filter(player->player.avg!=0 && player.sr!=0)
				.sorted((player1, player2) -> Double.compare(player1.sr+player1.avg, player2.sr+player2.avg))
				.collect(Collectors.toList());
		return sortedStrikeRateAndAverageList;
	}
	
	public List<IPLBowlingCSV> getBowlersWithMaxWicketsAndBestAverage(){
		List<IPLBowlingCSV> sortedWithMaxWicketsAndAverageList = IPLBowlingCSVList.stream()
				.filter(player->player.avg!=0)
				.sorted((player1, player2) -> Double.compare(player1.wkts+(1/player1.avg), player2.wkts+(1/player2.avg)))
				.collect(Collectors.toList());
		Collections.reverse(sortedWithMaxWicketsAndAverageList);
		return sortedWithMaxWicketsAndAverageList;
	}
	
	public List<IPLBattingCSV> getTopBatmenWithMax6s(){
		List<IPLBattingCSV> batmenWithMax6s = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.get6s(), player2.get6s()))
				.collect(Collectors.toList());
		Collections.reverse(batmenWithMax6s);
		return batmenWithMax6s ;
	}
	
	public List<IPLBattingCSV> getTopBatmenWithMax4s(){
		List<IPLBattingCSV> batmenWithMax4s = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.get4s(), player2.get4s()))
				.collect(Collectors.toList());
		Collections.reverse(batmenWithMax4s);
		return batmenWithMax4s ;
	}
	public List<IPLBattingCSV> getCricketerWithBestStrikingRateWith6sAnd4s(){
		int max4sAnd6s = IPLBattingCSVList.stream()
				.map(player -> (player.get4s()+player.get6s()))
				.max(Integer::compare)
				.get();
		List<IPLBattingCSV> batmenWithMax4sAnd6s = IPLBattingCSVList.stream()
				.filter((player -> (player.get6s()+player.get4s())==max4sAnd6s))
				.collect(Collectors.toList());
		
		double bestStrikingRate=batmenWithMax4sAnd6s.stream()
				.map(player -> player.getSR())
				.max(Double::compare)
				.get();
		
		List<IPLBattingCSV> batmenBestStrikingRateWithMax4sAnd6s = batmenWithMax4sAnd6s.stream()
				.filter(player->player.getSR()==bestStrikingRate)
				.collect(Collectors.toList());
		
		return batmenBestStrikingRateWithMax4sAnd6s ;
	}
	
	public List<IPLBattingCSV> getGreatestAverageWithBestStrikingRate(){
		double greatestAverage = IPLBattingCSVList.stream()
				.map(player ->player.getAverage())
				.max(Double::compare)
				.get();
		List<IPLBattingCSV> cricketerWithGreatestAverage=IPLBattingCSVList.stream()
				.filter(player->player.getAverage()==greatestAverage)
				.collect(Collectors.toList());
		double bestStrikeRate=cricketerWithGreatestAverage.stream()
				.map(player->player.getSR())
				.max(Double::compare)
				.get();
		List<IPLBattingCSV> batmenBestStrikingRateWithGreatestAverage =cricketerWithGreatestAverage.stream()
				.filter(player->player.getSR()==bestStrikeRate)
				.collect(Collectors.toList());
		
		return batmenBestStrikingRateWithGreatestAverage ;
	}
	
	public List<IPLBattingCSV> getMaximumRunWithGreatestAverage(){
		int maximumRun = IPLBattingCSVList.stream()
				.map(player ->player.getRuns())
				.max(Integer::compare)
				.get();
		List<IPLBattingCSV> cricketerWithMaximumRun=IPLBattingCSVList.stream()
				.filter(player->player.getRuns()==maximumRun)
				.collect(Collectors.toList());
		double greatestAverage=cricketerWithMaximumRun.stream()
				.map(player->player.getAverage())
				.max(Double::compare)
				.get();
		List<IPLBattingCSV> batmenBestStrikingRateWithGreatestAverage =cricketerWithMaximumRun.stream()
				.filter(player->player.getAverage()==greatestAverage)
				.collect(Collectors.toList());
		
		return batmenBestStrikingRateWithGreatestAverage ;
	}
	
	public List<String> getBestBattingAndBowlingAverage(){

		List<String> bestBattingAndBowlingAverage=new ArrayList<>();

		List<IPLBattingCSV> bestBattingAvg = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.avg,player2.avg))
				.collect(Collectors.toList());
		Collections.reverse(bestBattingAvg);

		List<IPLBowlingCSV> bestBowlingAvg=IPLBowlingCSVList.stream()
				.filter(player->player.avg!=0)
				.sorted((player1, player2) -> Double.compare(player1.avg, player2.avg))
				.collect(Collectors.toList());

		for (IPLBattingCSV batter : bestBattingAvg) {
			for (IPLBowlingCSV bowler : bestBowlingAvg) {
				if (batter.player.equals(bowler.player)) {
					bestBattingAndBowlingAverage.add(batter.player);
				}
			}
		}
		return bestBattingAndBowlingAverage;		
	}
	public List<String> getPlayerWithMostRunAndMostWicket(){
        List<String> playerWithMostRunAndMostWickets=new ArrayList<>();

		List<IPLBattingCSV> playerWithMostRun = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.runs,player2.runs))
				.collect(Collectors.toList());
		Collections.reverse(playerWithMostRun);

		List<IPLBowlingCSV> playerWithMostWicket=IPLBowlingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.wkts, player2.wkts))
				.collect(Collectors.toList());
		Collections.reverse(playerWithMostWicket);

		for (IPLBattingCSV batter : playerWithMostRun) {
			for (IPLBowlingCSV bowler : playerWithMostWicket) {
				if (batter.player.equals(bowler.player)) {
					playerWithMostRunAndMostWickets.add(batter.player);
				}
			}
		}
		return playerWithMostRunAndMostWickets;
	}
	public List<IPLBattingCSV> getPlayerWithMax100AndBestBattingAverage(){
		int highestCenturies=IPLBattingCSVList.stream()
				.map(player->player.num100)
				.max(Integer::compare)
				.get();

		double highestAverage=IPLBattingCSVList.stream()
				.map(player->player.avg)
				.max(Double::compare)
				.get();

		List<IPLBattingCSV> batmenWithMaxCenturyAndBestBattingAverage = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(((player1.num100/highestCenturies)+(player1.avg/highestAverage)), ((player2.num100/highestCenturies)+(player2.avg/highestAverage))))
				.collect(Collectors.toList());
		Collections.reverse(batmenWithMaxCenturyAndBestBattingAverage);
		return batmenWithMaxCenturyAndBestBattingAverage;
	}
	public List<IPLBattingCSV> getPlayerWithZeroCenturyOrHalfCenturyAndBestBattingAverage(){
		List<IPLBattingCSV> batmenWithZeroCenturyButBestBattingAverage = IPLBattingCSVList.stream()
				.filter(player->player.num100==0 && player.num50==0)
				.sorted((player1, player2) -> Double.compare(player1.avg,player2.avg))
				.collect(Collectors.toList());
		Collections.reverse(batmenWithZeroCenturyButBestBattingAverage);
		return batmenWithZeroCenturyButBestBattingAverage;
	}
}