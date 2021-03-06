import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;
import { Ticket } from 'src/models/ticket.model';
import {AdminService} from 'src/service/admin.service';
import {EmployeeService} from 'src/service/employee.service';

@Component({
  selector: 'app-assign-employee',
  templateUrl: './assign-employee.component.html',
  styleUrls: ['./assign-employee.component.css']
})
export class AssignEmployeeComponent implements OnInit {

  ticket : Ticket[] = [] ;
  tempTicket : Ticket[] = [];
  ticketBean: Ticket = new Ticket();

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
  sortedByTicketId: boolean = false;
  pendingAppoints: any[] = [];
  allTickets: any[] = [];
  temptickets: Ticket[];


  constructor(private route : Router , private service : AdminService, private employeeService : EmployeeService) { }

  ngOnInit() {

    this.employeeService.getTickets(sessionStorage.getItem('empId')).subscribe(
      res => {
        console.log(res)
        this.isLoaded = true
        this.ticket = res;
        this.tempTicket = res
        
      }
    )

  }
  
  //Sort by allocation id
  sortByTicketId() {
    this.ticket.sort(this.sortByProperty('ticketId'))
    this.sortedByTicketId = true
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

  viewPending() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.status == 'pending')
  }

  onKeyUpAll(event: any) {
    this.ticket = this.ticket.filter(ticketBean => ticketBean.title.includes(event.target.value))
    if (event.target.value == '' || event.target.value == undefined) {
      this.ticket = this.tempTicket
    }
  }

  viewOpen() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.status == 'open')
  }

  viewClose() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.status == 'close')
  }


  viewLow() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.criticalLevel == 'low')
  }

  viewNormal() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.criticalLevel == 'normal')
  }

  viewHigh() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.criticalLevel == 'high')
  }

  assignEmployee(ticketId: number){
    this.route.navigate(['/employee/dashboard/appointElse/',ticketId]);
  }
}
