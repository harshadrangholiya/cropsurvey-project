import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Survey } from 'src/app/survey';
import { SurveyService } from 'src/app/survey.service';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-cropdata',
  standalone: true,
  imports: [ 
    NgFor,
    FormsModule
    ],
  templateUrl: './cropdata.component.html',
  styleUrls: ['./cropdata.component.css']
})
export class CropdataComponent implements OnInit {

  survey : Survey = new Survey();
  
  constructor( private surveyService: SurveyService,
    private route: Router,
    private toast:NgToastService){

  }
  ngOnInit(): void {}

  saveSurvey(){
    this.surveyService.cropData(this.survey).subscribe(data=>{
      console.log(data);
      this.goToSurveyList();
    },
    error =>console.error(error));
    
  }
  goToSurveyList(){
    this.route.navigate([ '/home']);
  }
  onSubmit(){
    console.log(this.survey);
   
    this.saveSurvey();
    this.toast.success({detail:"Success Message",summary:"Crop-Data Added Successfully",duration:4000})
  }
}
