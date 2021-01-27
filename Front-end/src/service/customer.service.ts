import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from 'src/models/ticket.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
 
  myURL='http://localhost:8091/';

  constructor(private http: HttpClient) { }

  getTickets(custId : string){
    return this.http.get<any>("http://localhost:8091/viewTicketByCustId/"+custId);
  }

  updateTicket(ticketDetails:Ticket, ticketId : number){
    return this.http.put<Ticket>("http://localhost:8091/updateTicketByCustomer/"+ticketId, ticketDetails);
  }

  viewTicket(ticketId){
    return this.http.get<any>(`${this.myURL}viewTicketById/`+ticketId);
  }


  raiseTicket(formData: FormData) {
    return this.http.post<any>(`${this.myURL}upload`, formData);
  }
}
