
import { BrowserModule } from '@angular/platform-browser';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { RegisterComponent } from './components/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UserListComponent } from './components/user-list/user-list.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxUiLoaderHttpModule } from 'ngx-ui-loader'; 
import { LoginComponent } from './components/login/login.component';
import { MatDialogModule } from '@angular/material/dialog';
import { AngularMaterialModule } from './components/angular-material.module';
import { InterceptorService } from './components/interceptor.service';
import { CsvComponent } from './components/csv/csv.component';
import { GtpointComponent } from './components/gtpoint/gtpoint.component';
import { HomeComponent } from './components/home/home.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { JwtHelperService } from '@auth0/angular-jwt'
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgToastModule } from 'ng-angular-popup';
import { SidebarComponent } from './components/sidebar/sidebar.component';

import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ForgetPasswordComponent } from './components/forget-password/forget-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,    
    RegisterComponent,
    UserListComponent,
    CsvComponent,
    GtpointComponent,
    HomeComponent,
    SidebarComponent,
    ForgetPasswordComponent,
    ChangePasswordComponent
   
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    HttpClientModule,
    FormsModule, 
    AngularMaterialModule,
    ReactiveFormsModule,
    MatPaginatorModule,
   
    Ng2SearchPipeModule,
    NgxPaginationModule,
    NgxUiLoaderHttpModule.forRoot({ showForeground: true }),
    MatDialogModule,
    NgToastModule,
    MatToolbarModule,
    MatSnackBarModule
  ],
  providers: [
    {  
      provide: HTTP_INTERCEPTORS,  
      useClass: InterceptorService,  
      multi: true  
    } 
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }