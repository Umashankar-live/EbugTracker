import { Injectable } from '@angular/core';
import { Employee } from 'src/models/employee.model';
import { HttpClient } from '@angular/common/http';
import { Project } from 'src/models/project.model';


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


}




