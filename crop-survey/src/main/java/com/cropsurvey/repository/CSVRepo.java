package com.cropsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cropsurvey.model.Survey;


@Repository
public interface CSVRepo extends JpaRepository<Survey, Long> {

}
