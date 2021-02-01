import { Component, OnInit } from '@angular/core';
import { Project } from 'src/models/project.model';
import { AdminService } from 'src/service/admin.service';


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


  constructor(private service : AdminService) {
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
        console.log("response received");
        this.isadded = true;
        console.log(data);
        this.pro = data;
      },
      error => {
        this.isexist = true;
      })
  }

}
