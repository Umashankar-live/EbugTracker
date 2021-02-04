import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Project } from 'src/models/project.model';
import { AdminService } from 'src/service/admin.service';
import { NotificationService } from 'src/service/notification.service';


@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
 
  project : Project ;
  isadded: boolean = false;
  pro: Project;
  isexist: boolean = false;


  constructor( private router: Router,private service : AdminService,private notifyService : NotificationService) {
       this.project = new Project() ;
       this.project.backendTechno = null ;
       this.project.frontendTechno = null ;
               
   }


  ngOnInit() {
  }

  saveProject() {
    console.log(this.project)
    this.service.addProject(this.project).subscribe(
      data => {
        this.showToasterSuccess();
        console.log("response received");
        this.isadded = true;
        console.log(data);
        this.pro = data;
        this.router.navigate(["/admin/dashboard/listProject"]);
        
      },
      error => {
        this.isexist = true;
        this.showToasterFailure();
      })
  }

   showToasterSuccess(){
    this.notifyService.showSuccess("Data Successfully added !!", "Added Project");
} 


 showToasterFailure(){
   this.notifyService.showError("Data Failed to Add","Added Project")
 }

}
