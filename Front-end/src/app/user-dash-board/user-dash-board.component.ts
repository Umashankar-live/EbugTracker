import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth.service';

@Component({
  selector: 'app-user-dash-board',
  templateUrl: './user-dash-board.component.html',
  styleUrls: ['./user-dash-board.component.css']
})
export class UserDashBoardComponent implements OnInit {

  sidebarClass: string = ""
  menuToggleClass: string = "container1"
  testCount: object = null
  centerCount: object = null
  pendingCount: object = null

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
