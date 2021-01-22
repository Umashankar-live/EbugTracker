import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth.service';

@Component({
  selector: 'app-user-dash-board',
  templateUrl: './customer-dash-board.component.html',
  styleUrls: ['./customer-dash-board.component.css']
})
export class CustomerDashBoardComponent implements OnInit {

  sidebarClass: string = ""
  menuToggleClass: string = "container1"
  

  isLoadingCount: boolean = true
  isLoadingPending: boolean = true
  isLoadingCenters: boolean = true
  userId: string
  name: string
  isAppointmentExisit: boolean = true;
 



  constructor(private router: Router, private authService: AuthService) {

  }

  ngOnInit(): void {

    this.name = sessionStorage.getItem('uName');
    console.log("Name = " + this.name); 

  }


  getPersonalDetail() {

    this.userId = sessionStorage.getItem('custId')
    console.log(this.userId);
    this.router.navigate(['/customer/dashboard/PersonalDetails/', this.userId]);
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


 

}
