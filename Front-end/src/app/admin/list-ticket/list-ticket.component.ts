import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;
import { Ticket } from 'src/models/ticket.model';
import {AdminService} from 'src/service/admin.service';

@Component({
  selector: 'app-list-ticket',
  templateUrl: './list-ticket.component.html',
  styleUrls: ['./list-ticket.component.css']
})
export class ListTicketComponent implements OnInit {

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

  constructor(private route : Router , private service : AdminService) { }

  ngOnInit() {

    this.service.fetchAllTicket().subscribe(
      res => {
        console.log(res)
        this.isLoaded = true
        this.ticket = res;
        this.tempTicket = res
        this.sortByTicketId();
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

  viewProcessing() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.status == 'processing')
  }

  viewIntiated() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.status == 'intiated')
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


  logout() {
    sessionStorage.clear();
    this.route.navigate(['login']);
  }



}
