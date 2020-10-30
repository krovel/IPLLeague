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
	
	public void loadDataToList(String csvFile) throws IOException {
		Reader reader=Files.newBufferedReader(Paths.get(csvFile));
	    IPLBattingCSVList = new CsvToBeanBuilder(reader).withType(IPLBattingCSV.class).build().parse();
	}
	
	public List<IPLBattingCSV> getTopBattingAverages() throws Exception {
		List<IPLBattingCSV> sortedAvgList = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getAverage(), player2.getAverage()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}	
	public List<IPLBattingCSV> getTopStrikingRates() throws IOException {
		List<IPLBattingCSV> sortedStrikingRateList = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getSR(), player2.getSR()))
				.collect(Collectors.toList());
		Collections.reverse(sortedStrikingRateList);
		return sortedStrikingRateList;
	}
	public List<IPLBattingCSV> getTopBatmenWithMax6s() throws IOException {
		List<IPLBattingCSV> batmenWithMax6s = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.get6s(), player2.get6s()))
				.collect(Collectors.toList());
		Collections.reverse(batmenWithMax6s);
		return batmenWithMax6s ;
	}

	public List<IPLBattingCSV> getTopBatmenWithMax4s() throws IOException {
		List<IPLBattingCSV> batmenWithMax4s = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.get4s(), player2.get4s()))
				.collect(Collectors.toList());
		Collections.reverse(batmenWithMax4s);
		return batmenWithMax4s ;
	}
	public List<IPLBattingCSV> getCricketerWithBestStrikingRateWith6sAnd4s()throws IOException{
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
	public List<IPLBattingCSV> getGreatestAverageWithBestStrikingRate() throws IOException{
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
}