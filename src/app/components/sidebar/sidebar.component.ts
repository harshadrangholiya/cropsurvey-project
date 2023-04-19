import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from '../../auth.service';
import { UserStoreService } from '../../user-store.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  user : any={};
  isAdmin = false
  public role!: string;
  fullName:string = "";
  constructor(
    private auth: AuthService,
    private userStore : UserStoreService,
    private route:Router,
    private toast : NgToastService,
 ){}

  decodedToken(){

    const jwtHelper = new JwtHelperService();
    this.user = localStorage.getItem('token');
    this.user = jwtHelper.decodeToken(this.user);
     
    if(this.user.role == 'admin'){
      this.isAdmin = true;
    }
    if(this.user.role == 'user'){
      this.isAdmin = false;
    }
  
    
  }

  checkRole(){
    if(this.user.role == "admin"){
     
      this.isAdmin = true;
    }
    if(this.user.role == "user"){
      this.isAdmin = false;
    }
  }

  ngOnInit(): void {
    this.decodedToken();
    
     this.userStore.getFullNameFromStore()
     .subscribe(val=> {
       const fullNameFromToken = this.auth.getfullNameFromToken();
       this.fullName = val || fullNameFromToken;
     });
     this.userStore.getRoleFromStore().subscribe(val=>{
       const roleFromToken = this.auth.getRoleFromToken();
       this.role=val || roleFromToken;
     })
  }
  logout(){

    localStorage.clear();
    this.route.navigate(['/login'])
    this.toast.success({detail:"Logged Out", summary:"User Logged Out",duration:5000});
  }

 
}
