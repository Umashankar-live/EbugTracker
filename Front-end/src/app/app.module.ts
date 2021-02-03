import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { LoginComponent } from './login/login.component';
import { CustomerDashBoardComponent } from './customer-dash-board/customer-dash-board.component';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { ToastrModule } from 'ngx-toastr';


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
import { ListTicketComponent } from './admin/list-ticket/list-ticket.component';
import { UpdateTicketComponent } from './admin/update-ticket/update-ticket.component';
import { AssignTicketComponent } from './admin/assign-ticket/assign-ticket.component';
import { SelectEmployeeComponent } from './admin/select-employee/select-employee.component';
import { AssignedTicketComponent } from './employee/assigned-ticket/assigned-ticket.component';
import { SendSolutionComponent } from './employee/send-solution/send-solution.component';
import { AssignEmployeeComponent } from './employee/assign-employee/assign-employee.component';
import { AppointEmployeeComponent } from './employee/appoint-employee/appoint-employee.component';
import { ListProjectComponent } from './admin/list-project/list-project.component';





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
    ListTicketComponent,
    UpdateTicketComponent,
    AssignTicketComponent,
    SelectEmployeeComponent,
    AssignedTicketComponent,
    SendSolutionComponent,
    AssignEmployeeComponent,
    AppointEmployeeComponent,
    ListProjectComponent,
    
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    NgxPaginationModule,
    AppRoutingModule,
    ReactiveFormsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
