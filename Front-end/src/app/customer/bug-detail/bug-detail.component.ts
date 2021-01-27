import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router' ;
import { Ticket } from 'src/models/ticket.model';
import {CustomerService} from 'src/service/customer.service';

@Component({
  selector: 'app-bug-detail',
  templateUrl: './bug-detail.component.html',
  styleUrls: ['./bug-detail.component.css']
})
export class BugDetailComponent implements OnInit {
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

  constructor(private route : Router , private service : CustomerService) { }

  ngOnInit() {

    this.service.getTickets(sessionStorage.getItem('custId')).subscribe(
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


  onUpdate(form: NgForm) {

    if (form.valid) {
      let raisedTicket = new Ticket()
      raisedTicket.title = form.value.ticketTitle
      raisedTicket.description = form.value.ticketDesc
     
      

      this.service.updateTicket(raisedTicket,this.ticketId ).subscribe(res => {
        console.log(res)
        this.isUpdated = true

      })
    }

  }

  reload() {
    this.ngOnInit();
  }




  

}
