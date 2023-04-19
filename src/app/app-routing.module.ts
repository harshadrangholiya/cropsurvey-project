import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

import { UserListComponent } from './components/user-list/user-list.component';
import { HomeComponent } from './components/home/home.component';
import { CsvComponent } from './components/csv/csv.component';
import { GtpointComponent } from './components/gtpoint/gtpoint.component';
import { AuthGuard } from './auth.guard';
import { CropdataComponent } from './components/cropdata/cropdata.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ForgetPasswordComponent } from './components/forget-password/forget-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';


const routes: Routes = [
  { path: 'user-list', component: UserListComponent,canActivate:[AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent, canActivate:[AuthGuard] },
  { path: 'csv', component: CsvComponent,canActivate:[AuthGuard] },
  { path: 'gtpoint', component: GtpointComponent,canActivate:[AuthGuard] },
  { path: 'cropdata', component: CropdataComponent,canActivate:[AuthGuard] },
  { path: 'sidebar', component: SidebarComponent,canActivate:[AuthGuard]},
  {path:'forget-password',component: ForgetPasswordComponent},
  {path:'change-password',component: ChangePasswordComponent,canActivate:[AuthGuard]},
  { path: '**', redirectTo: 'login', pathMatch: 'full'},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
   
  exports: [RouterModule],
})
export class AppRoutingModule {}