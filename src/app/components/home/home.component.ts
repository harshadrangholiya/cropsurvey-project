import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Survey } from 'src/app/survey';
import { SurveyService } from 'src/app/survey.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  searchText: any;
  POSTS: any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 8;
  tableSizes: any = [3, 6, 9, 12];
  surveys: any;

  constructor(
    private surveyService: SurveyService,
    private router: Router,
    private toast: NgToastService,
    private http: HttpClient) { }
  downloadCsv() {
    this.http.get('http://localhost:8080/api/csv/download-csv', { responseType: 'blob' })
      .subscribe((blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'csvdemo.csv';
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
      });
  }


  ngOnInit(): void {

    this.getAllSurvey();
    this.surveyList();
  }


  getAllSurvey() {
    this.surveyService.getAllSurvey().subscribe((data: any) => {
      this.surveys = data;

    });
  }
  // private getSurvey() {
  //   this.surveyService.getSurveyList().subscribe((data: Survey[]) => {
  //     this.surveys = data;
  //   });
  // }

  logout() {
    localStorage.clear()
    this.router.navigate(['login'])
    this.toast.success({ detail: "Logged Out", summary: "Logout Successfully", duration: 5000 })
  }

  surveyList(): void {
    this.surveyService.getAllSurvey().subscribe(response => {
      this.POSTS = response;
      console.log(this.POSTS);
    })
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.surveyList();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.surveyList();
  }
}

