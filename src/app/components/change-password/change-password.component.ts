import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { MatDialogRef } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';

import { NgToastService } from 'ng-angular-popup';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user.service';
import { GlobalConstants } from 'src/app/global-constant';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
 oldPassword = true;
 newPassword = true;
 changePasswordForm:any = FormGroup;
 responseMessage:any;
  ngxService: any;
 constructor(private formBuilder: FormBuilder,
  private userService:UserService,
  private toast: NgToastService,
  private route: Router,
  ngxService:NgxUiLoaderService,
   ){}
  ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group({
      oldPassword:[null, Validators.required],
      newPassword:[null, Validators.required]
    })
  }
handlepasswordChangeSubmit(){
  // this.ngxService.start();
  var formData = this.changePasswordForm.value;
  var data = {
    oldPassword: formData.oldPassword,
    newPassword: formData.newPassword
  }
  this.userService.changePassword(data).subscribe((response:any) =>{
    // this.ngxService.stop();
    this.toast.success({detail:"Password Changed Successfuly ", summary:"",duration:3000})
    this.route.navigate(['/home']);
    this.responseMessage = response?.message;
    
  },(error:any)=>{
    console.log(error);
    // this.ngxService.stop();
    if(error.error?.message){
      this.responseMessage = error.error?.message;
      // this.toast.error({detail:"Enter Valid Password", summary:"Invalid Old Password",duration:3000})
    }
    else{
      this.responseMessage = GlobalConstants.genericError;
    }
  })

}

}