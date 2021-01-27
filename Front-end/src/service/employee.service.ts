import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from 'src/models/ticket.model';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  
  
  sendSolution(raisedTicket: Ticket, ticketId: number) {
    return this.http.put<any>("http://localhost:8094/employee/sendSolution/"+ticketId,raisedTicket);
  }
  

  
  
  getTickets(empId: string) {
    return this.http.get<any>(" http://localhost:8094/employee/getTicketlist/"+empId);
  }

  constructor(private http: HttpClient) { }
}
