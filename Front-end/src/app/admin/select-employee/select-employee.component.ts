import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/models/employee.model';
import { LoginserviceService } from 'src/service/loginservice.service';
import { AdminService } from 'src/service/admin.service';
import { NotificationService } from 'src/service/notification.service';


@Component({
  selector: 'app-select-employee',
  templateUrl: './select-employee.component.html',
  styleUrls: ['./select-employee.component.css']
})
export class SelectEmployeeComponent implements OnInit {

  ticketId: number;
  employee: Employee[] = [];
  tempEmployee: Employee[] = [];
  isLoading: boolean = true;
  employeeBean: Employee;

  bucketSize: number = 5;
  page: number = 1;

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




  constructor(private route: ActivatedRoute, private router: Router, private service: LoginserviceService, private adminService: AdminService, private notifyService: NotificationService) { }

  ngOnInit() {

    this.route.params.subscribe(x => this.ticketId = x['ticketId']);
    console.log(this.ticketId);
    this.service.fetchAllEmployee().subscribe(data => {
      this.employee = data;
      this.tempEmployee = data;
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
    this.employee = this.employee.filter(EmployeeBean => EmployeeBean.firstName.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined) {
      this.employee = this.tempEmployee;
    }
  }


  selectEmployee(empId: number, empName: string) {

    this.adminService.assignTicket(empId, empName, this.ticketId).subscribe(
      data => {
        console.log("response received");
        console.log(data);
        this.showToasterSuccess();
        this.router.navigate(["/admin/dashboard"]);

      })

  }

  showToasterSuccess() {
    this.notifyService.showSuccess("Raised Ticket is Assigned to Employee !!", "Assign Ticket");
  }


}
