import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user.service';
import { GlobalConstants } from 'src/app/global-constant';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { NgToastService } from 'ng-angular-popup';
import { MatDialogConfig } from '@angular/material/dialog';
import { ForgetPasswordComponent } from '../forget-password/forget-password.component';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent implements OnInit {
  email = '';
  password = '';
  hide = true;
  loginform: any = FormGroup;
  responseMessage: any;
  user: any;
  saveUser: any;
  token: any;
  dialog: any;


  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private ngxService: NgxUiLoaderService,
    private toast: NgToastService) { }

  ngOnInit(): void {
    this.loginform = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }


  handleSubmit() {
    this.ngxService.start();
    var formData = this.loginform.value;
    var data = {
      email: formData.email,
      password: formData.password
    }
    this.userService.loginUser(this.loginform.value).subscribe((response: any) => {
      console.log(response);
      this.ngxService.stop();

      localStorage.setItem('token', response.token);
      this.router.navigate(['/home']);

      this.toast.success({ detail: "Success Message", summary: "Login Successfully", duration: 5000 })
    }, (error: { error: { message: any; }; }) => {
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
        this.toast.error({ detail: "Error Message", summary: "Login Failed, Try again Later !!", duration: 5000 })
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }

    });
  }
  onSubmit() {
    console.log(this.user);
    this.loginUser();
  }

  loginUser() {
    localStorage.setItem('token', this.token);
    this.router.navigate(['/home'])
  }

}