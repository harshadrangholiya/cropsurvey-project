package com.cropsurvey.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cropsurvey.model.Survey;



public interface CSVService {

	public void save(MultipartFile file) ;

	public List<Survey> getAllSurveys();

	public InputStream load();
	
	Survey saveSurvey(Survey survey);
}
