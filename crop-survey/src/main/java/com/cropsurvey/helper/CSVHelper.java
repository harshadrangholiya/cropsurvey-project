package com.cropsurvey.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.cropsurvey.model.Survey;



public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Surveyor Name", "Verified Status", "Rejected Remarks","State" ,"District","Taluka","Village", "Current Latitude", "Current Longitude",
          "Surveyed Latitude","Surveyed Longitude","Date"};

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Survey> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Survey> surveys = new ArrayList<Survey>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
       
    	  Survey survey=new Survey(
    			  Long.parseLong(csvRecord.get("Id")),
                  csvRecord.get("Surveyor Name"),
                  Boolean.parseBoolean(csvRecord.get("Verified Status")),
                  csvRecord.get("Rejected Remarks"),
                  csvRecord.get("State"),
                  csvRecord.get("District"),
                  csvRecord.get("Taluka"),
                  csvRecord.get("Village"),
                  Double.parseDouble(csvRecord.get("Current Latitude")),
                  Double.parseDouble(csvRecord.get("Current Longitude")),
                  Double.parseDouble(csvRecord.get("Surveyed Latitude")),
                  Double.parseDouble(csvRecord.get("Surveyed Longitude")),
                  csvRecord.get("Date")
    			  );
    	  
    	  survey.getId();
          survey.getName();
          survey.getStatus();
          survey.getRemarks();
          survey.getState();
          survey.getDistrict();
          survey.getTaluka();
          survey.getVillage();
          survey.getCu_latitude();
          survey.getCu_longitude();
          survey.getSu_latitude();
          survey.getSu_longitude();
          survey.getDate();
       
              survey.setId(null);
              surveys.add(survey);
      }

      return surveys;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Survey> surveys) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Survey survey : surveys) {
        List<Object> data = Arrays.asList(

              survey.getId(),
              survey.getName(),
              survey.getStatus(),
              survey.getRemarks(),
              survey.getState(),
              survey.getDistrict(),
              survey.getTaluka(),
              survey.getVillage(),
              survey.getCu_latitude(),
              survey.getCu_longitude(),
              survey.getSu_latitude(),
              survey.getSu_longitude(),
              survey.getDate()
              
        		
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

