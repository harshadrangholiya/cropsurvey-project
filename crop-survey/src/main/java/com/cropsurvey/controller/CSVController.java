package com.cropsurvey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cropsurvey.helper.CSVHelper;
import com.cropsurvey.model.CSVResponse;
import com.cropsurvey.model.Survey;
import com.cropsurvey.service.CSVService;


@RestController

@RequestMapping("/api/csv")

public class CSVController {
	
		@Autowired
	  CSVService fileService;
		
		@Autowired
		CSVService csvService;

	@PostMapping("/upload")
	public ResponseEntity<CSVResponse> uploadFile(MultipartFile file) {
		
		 String message = "";

		    if (CSVHelper.hasCSVFormat(file)) {
		      try {
		        fileService.save(file);

		        message = "Uploaded the file successfully: " + file.getOriginalFilename();
		        return ResponseEntity.status(HttpStatus.OK).body(new CSVResponse(message));
		      } catch (Exception e) {
		        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CSVResponse(message));
		      }
		    }

		    message = "Please upload a csv file!";
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CSVResponse(message));
		  }

		
	@GetMapping("/surveys")
	public ResponseEntity<List<Survey>> getAllSurveys() {
		try {
		      List<Survey> surveys = fileService.getAllSurveys();

		      if (surveys.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(surveys, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }	
		
	}
	
	
	@GetMapping("/download-csv")
public ResponseEntity<Resource> getFile() {
		
		String fileName ="csvdemo.csv";	
		InputStreamResource file = new InputStreamResource(fileService.load());
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "csvdemo.csv");
		return ResponseEntity.ok()
				
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
		        .contentType(MediaType.parseMediaType("text/csv"))
		        .body(file);
	}
	
	@PostMapping(path="/addcropdata")    
    public ResponseEntity<Survey>saveSurvey(@RequestBody(required=true) Survey survey) {
        return new ResponseEntity<Survey>(csvService.saveSurvey(survey),HttpStatus.CREATED);
    }
	
}
