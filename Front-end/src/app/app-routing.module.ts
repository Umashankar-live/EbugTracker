import { NgModule } from '@angular/core';
import { AuthGuard } from './auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { LoginComponent } from './login/login.component';
import { UserDashBoardComponent } from './user-dash-board/user-dash-board.component';
import {EmployeeDashBoardComponent} from './employee-dash-board/employee-dash-board.component';
import { RegisterEmployeeComponent } from './admin/register-employee/register-employee.component';
import { AddProjectComponent } from './admin/add-project/add-project.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },

  {
    path: 'admin/dashboard', component: AdminDashBoardComponent, children: [
      { path: 'registerEmployee', component: RegisterEmployeeComponent },
      { path: 'addProject', component: AddProjectComponent }
    ], canActivate: [AuthGuard]
  },

  {
    path: 'user/dashboard', component: UserDashBoardComponent, children: [], canActivate: [AuthGuard]
  },

  {
    path: 'employee/dashboard', component: EmployeeDashBoardComponent, children: [], canActivate: [AuthGuard]
  },


  // { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


