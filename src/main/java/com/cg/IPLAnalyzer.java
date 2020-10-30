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
	
	public List<IPLBattingCSV> getTopBattingAverages(String csvFile) throws Exception {
		List<IPLBattingCSV> sortedAvgList = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getAverage(), player2.getAverage()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);
		return sortedAvgList;
	}	
	public List<IPLBattingCSV> getTopStrikingRates(String csvFile) throws Exception {
		List<IPLBattingCSV> sortedStrikingRateList = IPLBattingCSVList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getSR(), player2.getSR()))
				.collect(Collectors.toList());
		Collections.reverse(sortedStrikingRateList);
		return sortedStrikingRateList;
	}
}