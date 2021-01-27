import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;
import { Ticket } from 'src/models/ticket.model';
import {EmployeeService} from 'src/service/employee.service';

@Component({
  selector: 'app-assigned-ticket',
  templateUrl: './assigned-ticket.component.html',
  styleUrls: ['./assigned-ticket.component.css']
})
export class AssignedTicketComponent implements OnInit {

  ticket : Ticket[]= [] ;
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
  retrievedImage: any;
  base64Data: any
  ticketId: number;
  currentTitle : string 
  currentDescription: string

  constructor(private route : Router , private service : EmployeeService) { }

  ngOnInit() {

    this.service.getTickets(sessionStorage.getItem('empId')).subscribe(
      res => {
        console.log(res)
        this.isLoaded = true
        this.ticket = res;
        this.tempTicket = res
        
      }
    )

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


  



}
