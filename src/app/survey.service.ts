import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Survey } from './survey';
import { Observable } from 'rxjs';

// const endpoint = 'http://localhost:8080/api/csv/surveys';
@Injectable({
  providedIn: 'root'
})
export class SurveyService implements OnInit {
  getSurveyList() : Observable<Survey[]> {
  
      return this.httpClient.get<Survey[]>(`${this.baseURL}`);
    
  }
  private baseURL = "http://localhost:8080/api/csv/surveys";

  private url="http://localhost:8080/api/csv/addcropdata"
  constructor(private httpClient: HttpClient) { }

  getAllSurvey():Observable<Survey[]>{
    let header = new HttpHeaders();
    header.append("Authorization","Bearer "+ localStorage.getItem("token"));
  return this.httpClient.get<Survey[]>(`${this.baseURL}`,{ 'headers': header });
  }

  cropData(survey: Survey):Observable<Object>{
    return this.httpClient.post(`${this.url}`,survey);
  }


  ngOnInit(): void {
    
  }
}