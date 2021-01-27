import { NgModule } from '@angular/core';
import { AuthGuard } from './auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { LoginComponent } from './login/login.component';
import { CustomerDashBoardComponent } from './customer-dash-board/customer-dash-board.component';
import { EmployeeDashBoardComponent } from './employee-dash-board/employee-dash-board.component';
import { RegisterEmployeeComponent } from './admin/register-employee/register-employee.component';
import { AddProjectComponent } from './admin/add-project/add-project.component';
import { PersonalDetailsComponent } from './customer/personal-details/personal-details.component';
import { ListTicketComponent } from './admin/list-ticket/list-ticket.component';
import { UpdateTicketComponent } from './admin/update-ticket/update-ticket.component';
import { AssignTicketComponent } from './admin/assign-ticket/assign-ticket.component';
import { SelectEmployeeComponent } from './admin/select-employee/select-employee.component';
import { RaiseTicketComponent } from './customer/raise-ticket/raise-ticket.component';
import { BugDetailComponent } from './customer/bug-detail/bug-detail.component';
import { AssignedTicketComponent } from './employee/assigned-ticket/assigned-ticket.component';
import { SendSolutionComponent } from './employee/send-solution/send-solution.component';
import { AssignEmployeeComponent } from './employee/assign-employee/assign-employee.component';
import { AppointEmployeeComponent } from './employee/appoint-employee/appoint-employee.component';





const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },

  {
    path: 'admin/dashboard', component: AdminDashBoardComponent, children: [
      { path: 'registerEmployee', component: RegisterEmployeeComponent },
      { path: 'addProject', component: AddProjectComponent },
      { path: 'pendingRequest', component: ListTicketComponent },
      { path: 'updateTicket', component: UpdateTicketComponent },
      { path: 'assignTicket', component: AssignTicketComponent },
      {path: 'selectEmployee/:ticketId',component:SelectEmployeeComponent}
    ], canActivate: [AuthGuard]
  },

  {
    path: 'customer/dashboard', component: CustomerDashBoardComponent, children: [

      { path: 'PersonalDetails/:userId', component: PersonalDetailsComponent },
      {path:'raiseTicket',component:RaiseTicketComponent},
      {path:'bugDetails',component:BugDetailComponent}

    ], canActivate: [AuthGuard]
  },

  {
    path: 'employee/dashboard', component: EmployeeDashBoardComponent, children: [
      {path:'assignedTicket' , component : AssignedTicketComponent},
      {path:'sendSolution',component:SendSolutionComponent},
      {path:'assignEmployee',component:AssignEmployeeComponent},
      {path:'appointElse/:ticketId',component:AppointEmployeeComponent}
    ], canActivate: [AuthGuard]
  },


  // { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


