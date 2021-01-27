import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/models/employee.model';
import { LoginserviceService } from 'src/service/loginservice.service';
import {EmployeeService} from 'src/service/employee.service';

@Component({
  selector: 'app-appoint-employee',
  templateUrl: './appoint-employee.component.html',
  styleUrls: ['./appoint-employee.component.css']
})
export class AppointEmployeeComponent implements OnInit {
  ticketId: number;
  employee: Employee[] = [];
  tempEmployee : Employee[] = [] ;
  isLoading: boolean = true;
  employeeBean : Employee ;

  bucketSize: number = 5 ;
  page : number = 1 ;

  //Flags required for interactive UI
  isAdded: boolean = null
  isUpdated: boolean = false
  isLoaded: boolean = false
  isErrorUpdating: boolean = false

  sortedById: boolean = null
  sortedByName: boolean = null
  sortedByDes: boolean = null
  isDeleteError: boolean = false
  sortedByEmployeeId: boolean = false;
  
  
 

  constructor(private route: ActivatedRoute, private router: Router, private service: LoginserviceService, private employeeService : EmployeeService) { }

  ngOnInit() {

    this.route.params.subscribe(x => this.ticketId = x['ticketId']);
    console.log(this.ticketId);
    this.service.fetchAllEmployee().subscribe(data => {
      this.employee = data;
      this.tempEmployee = data ;
      console.log(this.employee);
      this.isLoading = false
    });

  }

  //Sort by allocation id
  sortByEmployeeId() {
    this.employee.sort(this.sortByProperty('empId'))
    this.sortedByEmployeeId = true
  }

  //Function to sort property of an array
  sortByProperty(property) {
    return function (a, b) {
      if (a[property] > b[property])
        return 1;
      else if (a[property] < b[property])
        return -1;

      return 0;
    }
  }

  onKeyUpAll(event: any) {
    this.employee = this.employee.filter(EmployeeBean => EmployeeBean.userName.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined) {
      this.employee = this.tempEmployee ;
    }
  }

  
  selectEmployee(empId: number){

    this.employeeService.assignTicket(empId , this.ticketId).subscribe(
      data => {
        console.log("response received");
        console.log(data);
        alert("Raised Ticket is Assigned to Employee !!!")
        this.router.navigate(["/employee/dashboard"]);
        
      })
    
  }

}
