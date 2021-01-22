import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Login } from 'src/models/login.model';
import { Employee } from 'src/models/employee.model';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {




  login_url = "http://localhost:9002/EtrackingSystem/login";
  registration_url = "http://localhost:9002/EtrackingSystem/user/addUser";
  getUser_url = "http://localhost:9002/EtrackingSystem/user/searchUser/"


  constructor(private http: HttpClient) { }

  public loginUser(login: Login) {
    return this.http.post<Employee>(this.login_url, login)
  }

  public registerUser(user: User): Observable<any> {
    return this.http.post<any>(this.registration_url, user)
  }


  public getUserByUserId(userId: string) {
    return this.http.get<Employee>(this.getUser_url + userId);
  }

  updateUser(user: Employee, userId: string) {
    return this.http.put<Employee>("http://localhost:9002/EtrackingSystem/user/updateUser/"+userId, user);
  }

}
