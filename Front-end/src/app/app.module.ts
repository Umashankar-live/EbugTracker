import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { LoginComponent } from './login/login.component';
import { CustomerDashBoardComponent } from './customer-dash-board/customer-dash-board.component';
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
import { RaiseTicketComponent } from './customer/raise-ticket/raise-ticket.component';
import { BugDetailComponent } from './customer/bug-detail/bug-detail.component';
import { PersonalDetailsComponent } from './customer/personal-details/personal-details.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AppComponent,
    LoginComponent,
    AdminDashBoardComponent,
    CustomerDashBoardComponent,
    RegisterEmployeeComponent,
    AddProjectComponent,
    EmployeeDashBoardComponent,
    RaiseTicketComponent,
    BugDetailComponent,
    PersonalDetailsComponent,
    
    
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
