import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from 'src/models/login.model';
import { User } from 'src/models/user.model';
import { LoginserviceService } from 'src/service/loginservice.service';
import {AuthService} from 'src/service/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  user: User;
  userr: User;
  isFailed: boolean = false

  //custId : string = null
  emailId: string = null
  password: string = null
  userType: string = null


  loginSucc: boolean = true
  isLogginIn: boolean = false
  isInvalidAttempt: boolean = false
  inputType: string = "password"
  regType: string = "password"
  errMsg: string = "Please enter correct credential.."

  constructor(private router: Router, private loginservice: LoginserviceService, private route: ActivatedRoute,
    private Auth: AuthService) {
    this.user = new User();
    this.userr = new User();
   

  }

  ngOnInit(): void {
    
  }


  registerUser() {
    console.log(this.user)
    this.loginservice.registerUser(this.user).subscribe(
      data => {
        console.log("response received");
        alert('User Registration Successful! Please Login.')
        console.log(data);

      },
      error => {
        console.log("exception occured")
        this.isFailed = true ;
      })
  }




  //For view password
  toggle() {
    if (this.inputType == "password")
      this.inputType = "text"
    else
      this.inputType = "password"
  }


  //For view password
  regToggle() {
    if (this.regType == "password")
      this.regType = "text"
    else
      this.regType = "password"
  }


  onSubmit(form: NgForm) {
    this.isLogginIn = true
    if (form.valid) {
      let login = new Login();

      login.emailId = this.emailId
      login.password = this.password
      console.log(login)
      this.loginservice.loginUser(login).subscribe(
        res => {console.log(res);
          
          if (res == null) {
            this.loginSucc = false
            this.isLogginIn = false
          }
          else if (res.role == "admin") {
            sessionStorage.setItem('custId', res.empId)
            sessionStorage.setItem('userType', window.btoa("admin"))
            this.Auth.setLoggedIn(true);
            this.router.navigate(['admin/dashboard'])

          }
          else if (res.role == "customer") {
            sessionStorage.setItem('custId', res.empId)
            sessionStorage.setItem('uName', res.firstName)
            sessionStorage.setItem('userType', window.btoa("customer"))
            this.Auth.setLoggedIn(true);
            this.router.navigate(['customer/dashboard'])
          }
          else if (res.role == "employee") {
            sessionStorage.setItem('empId', res.empId)
            sessionStorage.setItem('eName', res.firstName)
            sessionStorage.setItem('userType', window.btoa("employee"))
            this.Auth.setLoggedIn(true);
            this.router.navigate(['employee/dashboard'])
          }
        }
      )
    }
  }

}
