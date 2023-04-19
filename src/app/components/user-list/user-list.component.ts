import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/user.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { NgToastService } from 'ng-angular-popup';



@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  searchText: any
  POSTS: any;
  USERS:any;
  page: number = 1;
  count: number = 0;
  tableSize: number = 5;
  tableSizes: any = [3, 6, 9, 12];

  [x: string]: any;

  dialog: any;
  users: User[] | undefined; 
  constructor(private userService: UserService,
    private router: Router,
    private toast:NgToastService) { }

  ngOnInit(): void {


    this.getAllUser();
    this.userList();

  }

  getAllUser() {
    this.userService.getAllUser().subscribe((data: any) => {
      this.users = data;

    });
  }
  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe((data: any) => {
      this.getAllUser();
    });
  }

  inActiveUser(id: number) {
    this.userService.inActiveUser(id).subscribe((data: any) => {
      this.getAllUser();
    });
  }

  activeUser(id: number) {
    this.userService.activeUser(id).subscribe((data: any) => {
      this.getAllUser();
    });
  }


  logout() {
    localStorage.clear()
    this.router.navigate(['login'])
    this.toast.success({detail:"Logged Out",summary:"Logout Successfully",duration:5000})
  }

  userList(): void {
    this.userService.getAllUser().subscribe((response) => {
      this.users = response;
      console.log(this.users);
    },(error) => {
      console.log(error);
    }
    )
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.userList();
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.userList();
  }
}
