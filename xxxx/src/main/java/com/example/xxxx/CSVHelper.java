package com.example.xxxx;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (TYPE.equals(file.getContentType())
	    		|| file.getContentType().equals("application/vnd.ms-excel")) {
	      return true;
	    }

	    return false;
	  }

	  public static List<DeveloperTutorial> csvToTutorials(InputStream is) {
		  System.out.println("csvToRutorials");
	    try {
	    	BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        @SuppressWarnings("resource")
			CSVParser csvParser = new CSVParser(fileReader,CSVFormat.RFC4180);	
	      List<DeveloperTutorial> developerTutorialList = new ArrayList<>();
	      System.out.println("csvToRutorials1");
	      CSVRecord csvRecords = csvParser.getRecords().get(0);
	      System.out.println("xx:"+csvRecords.get(1));
	      /*if (csvRecords instance of Collection<?>) {
	    	  System.out.println(((Collection<?>)csvRecords).size());
	    	}
	      System.out.println("csvToRutorials2");
	      /*for (CSVRecord csvRecord : csvRecords) {
	    	  DeveloperTutorial developerTutorial = new DeveloperTutorial(
	              Long.parseLong(csvRecord.get("Id")),
	              csvRecord.get("Title"),
	              csvRecord.get("Description"),
	              Boolean.parseBoolean(csvRecord.get("Published"))
	            );System.out.println("estoy iterando");
	    	  System.out.println("cosa id"+developerTutorial.getId());
	    	  System.out.println("cosa:"+developerTutorial);
	    	  developerTutorialList.add(developerTutorial);
	      }*/
	      System.out.println("csvToRutorials3");
	      return developerTutorialList;
	    } catch (IOException e) {
	    	System.out.println(e);
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

	  public static ByteArrayInputStream tutorialsToCSV(List<DeveloperTutorial> developerTutorialList) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (DeveloperTutorial developerTutorial : developerTutorialList) {
	        List<String> data = Arrays.asList(
	              String.valueOf(developerTutorial.getId()),
	              developerTutorial.getTitle(),
	              developerTutorial.getDescription(),
	              String.valueOf(developerTutorial.isPublished())
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }
	}
