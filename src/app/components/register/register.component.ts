import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { User } from 'src/app/user';
import { UserService } from 'src/app/user.service';
import { NgToastService } from 'ng-angular-popup';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();

  constructor(private userService: UserService, private router: Router,
    private toast:NgToastService) { }
  ngOnInit(): void {
  }
  saveUser() {
    this.userService.registerUser(this.user).subscribe((data: any) => {
      console.log(data);
      
      this.goToUserList();
    },
      (error: any) => console.log(error));
      
  }
  goToUserList() {

    this.router.navigate(['/user-list']);
    this.toast.success({detail:"Success Message",summary:" Successfully registered",duration:5000})
  }
  onSubmit() {
    console.log(this.user);
    this.saveUser();
  }
}