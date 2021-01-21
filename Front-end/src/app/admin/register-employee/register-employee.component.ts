import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/models/employee.model';
import { AdminService } from 'src/service/admin.service';

@Component({
  selector: 'app-register-employee',
  templateUrl: './register-employee.component.html',
  styleUrls: ['./register-employee.component.css']
})
export class RegisterEmployeeComponent implements OnInit {

  employee: Employee;
  emp: Employee;
  msg: string;
  isadded: boolean = false;
  isexist: boolean = false;
  regType: string;

  constructor(private service: AdminService) {
    this.employee = new Employee();
  }

  ngOnInit() {
  }

  saveEmployee() {
    console.log(this.employee)
    this.service.registerEmployee(this.employee).subscribe(
      data => {
        console.log("response received");
        this.isadded = true;
        //alert('Employee Registration Successful! Please Login.')
        console.log(data);
        this.emp = data;
      },
      error => {
        this.isexist = true;
      })
  }


   //For view password
   regToggle() {
    if (this.regType == "password")
      this.regType = "text"
    else
      this.regType = "password"
  }

}
