import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFileService } from 'src/app/uploadfile.service';


@Component({
  selector: 'app-csv',
  templateUrl: './csv.component.html',
  styleUrls: ['./csv.component.css'],
})
export class CsvComponent implements OnInit {
  progress: any;
  currentFileUpload: any;
  selectedFiles: any;

  constructor(
    private uploadService: UploadFileService,
    private route: Router
  ) { }
  ngOnInit(): void { }

  upload() {
    // this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService
      .pushFileToStorage(this.currentFileUpload)
      .subscribe((event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          // this.progress.percentage = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          alert('File Successfully Uploaded');
        }
        
        this.selectedFiles = undefined;
      });
  }
  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }
}