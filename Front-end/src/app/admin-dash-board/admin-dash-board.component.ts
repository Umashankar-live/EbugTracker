import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth.service';
import {AdminService} from 'src/service/admin.service' ;

@Component({
  selector: 'app-admin-dash-board',
  templateUrl: './admin-dash-board.component.html',
  styleUrls: ['./admin-dash-board.component.css']
})
export class AdminDashBoardComponent implements OnInit {

  sidebarClass: string = ""
  menuToggleClass: string = "container1"
  employeeCount: object = null
  projectCount: object = null
  pendingCount: object = null

  isLoadingCount: boolean = true
  isLoadingPending: boolean = true
  isLoadingProject: boolean = true


  constructor(private router: Router,  private authService: AuthService, private adminService : AdminService) { }

  ngOnInit(): void {

    this.adminService.countEmployee().subscribe(
      res => {
        this.employeeCount = res
        this.isLoadingCount = false
      }
    )

    this.adminService.countProject().subscribe(
      res => {
        this.projectCount = res
        this.isLoadingProject = false
      }
    )

    this.adminService.countPending().subscribe(
      res => {
        this.pendingCount = res
        this.isLoadingPending = false
      }
    )
    

  }

  logOut() {
    sessionStorage.clear();
    this.authService.setLoggedIn(false);
    this.router.navigate([''])
  }

  //For toggle
  toggleClass() {
    if (this.sidebarClass == "") {
      this.sidebarClass = "toggled"
      this.menuToggleClass = "container1  clickable"
    }
    else {
      this.sidebarClass = ""
      this.menuToggleClass = "container1 clickable"
    }
  }

  adminRefresh() {
    this.ngOnInit();
    this.router.navigate(["/admin/dashboard"]);
  }

}
