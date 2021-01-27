import { Injectable } from '@angular/core';
import { Employee } from 'src/models/employee.model';
import { HttpClient } from '@angular/common/http';
import { Project } from 'src/models/project.model';
import { Ticket } from 'src/models/ticket.model';


@Injectable({
  providedIn: 'root'
})

export class AdminService {
 


  constructor(private http: HttpClient) {

  }


  registerEmployee(employee: Employee) {
    return this.http.post<any>("http://localhost:9090/admin/registerEmployee", employee);
  }

  addProject(project: Project) {
    return this.http.post<any>("http://localhost:9090/admin/createproject", project);
  }

  countPending() {
    return this.http.get("http://localhost:9090/admin/countPending");
  }


  countProject() {
    return this.http.get("http://localhost:9090/admin/countProject");

  }

  countEmployee() {
    return this.http.get("http://localhost:9090/admin/countEmployee");

  }

  fetchAllTicket() {
    return this.http.get<Ticket[]>("http://localhost:9090/admin/tickets")
  }

  updateTicket(raisedTicket: Ticket, ticketId: number) {
    return this.http.put<Ticket>("http://localhost:9090/admin/updateticket/"+ticketId, raisedTicket);
  }

  assignTicket(empId: number, ticketId: number) {

     return this.http.get<Ticket>("http://localhost:9090/admin/assignticket/"+ticketId+"/"+empId);
   
  }

  fetchAllTicketForUpdate() {
    return this.http.get<Ticket[]>("http://localhost:9090/admin/updateticketList")
  }
  
  
  
  


}
