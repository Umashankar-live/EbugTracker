import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router' ;
import { Project} from 'src/models/project.model';
import {AdminService} from 'src/service/admin.service';

@Component({
  selector: 'app-list-project',
  templateUrl: './list-project.component.html',
  styleUrls: ['./list-project.component.css']
})
export class ListProjectComponent implements OnInit {

  bucketSize: number = 5 ;
  page : number = 1 ;
  project: Project[] =[];
  isLoaded: boolean = false
  tempProject: Project[] = [];

  constructor(private route : Router , private service : AdminService) { }

  ngOnInit() {
    this.service.fetchAllProject().subscribe(
      res => {
        console.log(res)
        this.isLoaded = true
        this.project = res
        this.tempProject = res
        
  })

}


onKeyUpAll(event: any) {
  this.project = this.project.filter(projectBean => projectBean.projectName.includes(event.target.value))
  if (event.target.value == '' || event.target.value == undefined) {
    this.project = this.tempProject;
  }
}

}
