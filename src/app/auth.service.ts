import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt'
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
   private userPayload:any;
  constructor(private toast : NgToastService, private router:Router) { 
    this.userPayload = this.decodedToken();
  }

  loggedIn(){
    return !!localStorage.getItem('token')
  }
  logout(){

    localStorage.clear();
    this.router.navigate(['/login'])
    this.toast.success({detail:"Logged Out", summary:"User Logged Out",duration:5000});
  }

  getToken(){
    return localStorage.getItem('token')
  }

  decodedToken(){
    const jwtHelper = new JwtHelperService();
    const token = this.getToken();
    console.log(jwtHelper.decodeToken())
    return jwtHelper.decodeToken();
  }
  getfullNameFromToken(){
    if(this.userPayload)
    return this.userPayload.name;
  }

  getRoleFromToken(){
    if(this.userPayload)
    return this.userPayload.role;
 }
}