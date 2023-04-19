package com.cropsurvey.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.cropsurvey.helper.CSVHelper;
import com.cropsurvey.model.Survey;
import com.cropsurvey.repository.CSVRepo;
import com.cropsurvey.service.CSVService;

@Service
public class CSVServiceImpl  implements CSVService{

	
	@Autowired
	CSVRepo csvRepo;
	
	
	
	public void save(MultipartFile file) {
		try {
		      List<Survey> surveys = CSVHelper.csvToTutorials(file.getInputStream());
		      surveys.forEach(survey-> csvRepo.save(survey));
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
  }

	 public ByteArrayInputStream load() {
		    List<Survey> surveys = csvRepo.findAll();

		    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(surveys);
		    return in;
		    
		  }
//
//		  public List<Survey> getAllTutorials() {
//		    return csvRepo.findAll();
//		   
//		  }

		@Override
		public List<Survey> getAllSurveys() {
			 return csvRepo.findAll();
		}

		@Override
		public Survey saveSurvey(Survey survey) {
			 return csvRepo.save(survey);
		}

		

}
