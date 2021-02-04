import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/models/ticket.model';
import { AdminService } from 'src/service/admin.service';
import { NgForm } from '@angular/forms';
import { NotificationService } from 'src/service/notification.service';

@Component({
  selector: 'app-update-ticket',
  templateUrl: './update-ticket.component.html',
  styleUrls: ['./update-ticket.component.css']
})
export class UpdateTicketComponent implements OnInit {


 
  currentstatus: string = null;
  criticalLevel: string = null;
  projectName: string;
  ticketId : number

  ticket: Ticket[] = [];
  tempTicket: Ticket[] = [];
  ticketBean: Ticket = new Ticket();

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
  sortedByTicketId: boolean = false;
  pendingAppoints: any[] = [];
  allTickets: any[] = [];
  temptickets: Ticket[];

  constructor(private router: ActivatedRoute,private route: Router, private service: AdminService,private notifyService : NotificationService) {

    
   }

  ngOnInit() {

    this.service.fetchAllTicketForUpdate().subscribe(
      res => {
        console.log(res)
        this.isLoaded = true
        this.ticket = res
        this.tempTicket = res
        this.sortByTicketId();
      }
    )

  }

  onUpdate(form: NgForm) {

    if (form.valid) {
      let raisedTicket = new Ticket()
      raisedTicket.projectName = form.value.projectNAme
      raisedTicket.status = form.value.currentStatus
      raisedTicket.criticalLevel = form.value.currentLevel
      

      this.service.updateTicket(raisedTicket,this.ticketId ).subscribe(res => {
        console.log(res)
        this.isUpdated = true;
        this.showToasterSuccess();

      })
    }
  }

  showToasterSuccess(){
    this.notifyService.showSuccess("Ticket Successfully Updated !!", "Update Ticket");
} 

  reload() {
    this.ngOnInit();
  }

  //Sort by allocation id
  sortByTicketId() {
    this.ticket.sort(this.sortByProperty('id'))
    this.sortedByTicketId = true
  }

  //Function to sort property of an array
  sortByProperty(property) {
    return function (a, b) {
      if (a[property] > b[property])
        return 1;
      else if (a[property] < b[property])
        return -1;

      
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

  viewFalse() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.isResolved == false)
  }

  viewTrue() {
    this.ticket = this.tempTicket.filter(ticketBean => ticketBean.isResolved == true)
  }



  logout() {
    sessionStorage.clear();
    this.route.navigate(['login']);
  }



}
