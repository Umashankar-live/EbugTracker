import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { LoginComponent } from './login/login.component';
import { UserDashBoardComponent } from './user-dash-board/user-dash-board.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';
import { RegisterEmployeeComponent } from './admin/register-employee/register-employee.component';
import { AddProjectComponent } from './admin/add-project/add-project.component';
import { EmployeeDashBoardComponent } from './employee-dash-board/employee-dash-board.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AppComponent,
    LoginComponent,
    AdminDashBoardComponent,
    UserDashBoardComponent,
    RegisterEmployeeComponent,
    AddProjectComponent,
    EmployeeDashBoardComponent,
    
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    NgxPaginationModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
