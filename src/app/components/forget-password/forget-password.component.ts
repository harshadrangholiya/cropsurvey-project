import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { GlobalConstants } from 'src/app/global-constant';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
  forgetPasswordForm: any = FormGroup;
  responseMessage:any;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private route : Router,
    private toast: NgToastService

  ){}
  ngOnInit(): void {
    this.forgetPasswordForm = this.formBuilder.group({
      email:[null,[Validators.required,Validators.pattern]]
    });
  }
   handleSubmit(){
     var formData = this.forgetPasswordForm.value;
     var data = {
       email : formData.email
     }
     this.userService.forgetPassword(data).subscribe((response:any)=>{
      this.toast.success({detail:"Email sent Successfuly ", summary:"",duration:3000})
      this.route.navigate(['/login']);
      
       this.responseMessage = response?.message;
     },(error)=>{
       if(error.error?.message){
         this.responseMessage = error.error?.message;
       }
       else{
         this.responseMessage = GlobalConstants.genericError;
       }
     })
   }

}