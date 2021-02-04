import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from 'src/service/loginservice.service';
import { Employee } from 'src/models/employee.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NotificationService } from 'src/service/notification.service';

@Component({
  selector: 'app-personal-details',
  templateUrl: './personal-details.component.html',
  styleUrls: ['./personal-details.component.css']
})
export class PersonalDetailsComponent implements OnInit {

  custId: string;
  user: Employee;


  currentFirstName: string
  currentLastName: string
  currentEmail: string
  currentMobile: string
  currentPassword: number
  isErrorUpdating: boolean = false;
  isUpdated: boolean;
  regType: string;


  constructor(private route: ActivatedRoute, private router: Router, private service: LoginserviceService,private notifyService : NotificationService) {

    this.user = new Employee();

  }


  ngOnInit() {

    this.route.params.subscribe(x => this.custId = x['userId']);
    console.log(this.custId);
    this.service.getUserByUserId(this.custId).subscribe(data => {
      this.user = data
      console.log(this.user);

    });

  }


  showToasterSuccess(){
    this.notifyService.showSuccess("Details Successfully Updated !!", "Personal Details");
} 

  onUpdate(form: NgForm) {

    if (form.valid) {
      let user = new Employee()
      user.firstName = form.value.firstName
      user.lastName = form.value.lastName
      user.emailId = form.value.emailId
      user.mobileNo = form.value.mobileNumber
      user.password = form.value.password
      // user.userId = sessionStorage.getItem('custId')

      this.service.updateUser(user, sessionStorage.getItem('custId')).subscribe(res => {
        console.log(res)
        this.isUpdated = true
        this.showToasterSuccess();

      })
    }
  }

  reload() {
    this.ngOnInit();
  }



  //For view password
  regToggle() {
    if (this.regType == "password")
      this.regType = "text"
    else
      this.regType = "password"
  }
  
}
